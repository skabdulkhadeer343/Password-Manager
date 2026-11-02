package com.securevault.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.securevault.database.entity.CredentialEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CredentialDao {

    @Query("SELECT * FROM credentials ORDER BY updatedAt DESC")
    fun getAllCredentials(): Flow<List<CredentialEntity>>

    @Query("SELECT * FROM credentials WHERE id = :id")
    suspend fun getCredentialById(id: String): CredentialEntity?


    @Query("SELECT isFavorite FROM credentials WHERE id = :id")
    suspend fun getFavoriteStatus(id: String): Boolean?
\n    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCredential(credential: CredentialEntity)

    @Update
    suspend fun updateCredential(credential: CredentialEntity)

    @Query("DELETE FROM credentials WHERE id = :id")
    suspend fun deleteCredential(id: String)

    @Query("SELECT * FROM credentials WHERE isFavorite = 1 ORDER BY updatedAt DESC")
    fun getFavoriteCredentials(): Flow<List<CredentialEntity>>

    @Query("""
        SELECT * FROM credentials
        WHERE title LIKE '%' || :query || '%'
        OR website LIKE '%' || :query || '%'
        ORDER BY updatedAt DESC
    """)
    fun searchCredentials(query: String): Flow<List<CredentialEntity>>
}
