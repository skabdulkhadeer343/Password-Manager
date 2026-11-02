package com.securevault.vault.repository

import com.securevault.crypto.CryptoManager
import com.securevault.database.dao.CredentialDao
import com.securevault.database.entity.CredentialEntity
import com.securevault.vault.model.Credential
import com.securevault.vault.model.CredentialCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VaultRepositoryImpl(
    private val credentialDao: CredentialDao,
    private val cryptoManager: CryptoManager
) : VaultRepository {

    override fun getAllCredentials(): Flow<List<Credential>> {
        return credentialDao.getAllCredentials().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override fun getFavoriteCredentials(): Flow<List<Credential>> {
        return credentialDao.getFavoriteCredentials().map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override fun searchCredentials(query: String): Flow<List<Credential>> {
        return credentialDao.searchCredentials(query).map { entities ->
            entities.map { it.toDomainModel() }
        }
    }

    override suspend fun getCredentialById(id: String): Credential? {
        return credentialDao.getCredentialById(id)?.toDomainModel()
    }

    override suspend fun addCredential(credential: Credential) {
        credentialDao.insertCredential(credential.toEntity())
    }

    override suspend fun updateCredential(credential: Credential) {
        val existingFavorite = credentialDao.getFavoriteStatus(credential.id) ?: false
        credentialDao.updateCredential(
            credential.toEntity(isFavorite = existingFavorite)
        )
    }

    override suspend fun deleteCredential(id: String) {
        credentialDao.deleteCredential(id)
    }

    private fun Credential.toEntity(
        isFavorite: Boolean = false
    ): CredentialEntity {
        return CredentialEntity(
            id = id,
            title = title,
            website = website,
            usernameEncrypted = cryptoManager.encrypt(username),
            passwordEncrypted = cryptoManager.encrypt(password),
            notesEncrypted = notes?.let { cryptoManager.encrypt(it) },
            category = category.name,
            isFavorite = isFavorite,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    private fun CredentialEntity.toDomainModel(): Credential {
        return Credential(
            id = id,
            title = title,
            website = website,
            username = cryptoManager.decrypt(usernameEncrypted),
            password = cryptoManager.decrypt(passwordEncrypted),
            notes = notesEncrypted?.let { cryptoManager.decrypt(it) },
            category = CredentialCategory.valueOf(category),
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}
