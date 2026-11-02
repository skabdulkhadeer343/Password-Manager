package com.securevault.vault.repository

import com.securevault.vault.model.Credential
import kotlinx.coroutines.flow.Flow

interface VaultRepository {

    fun getAllCredentials(): Flow<List<Credential>>

    fun getFavoriteCredentials(): Flow<List<Credential>>

    fun searchCredentials(query: String): Flow<List<Credential>>

    suspend fun getCredentialById(id: String): Credential?

    suspend fun addCredential(credential: Credential)

    suspend fun updateCredential(credential: Credential)

    suspend fun deleteCredential(id: String)
}
