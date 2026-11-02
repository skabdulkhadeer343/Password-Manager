package com.securevault.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.securevault.database.dao.CredentialDao
import com.securevault.database.entity.CredentialEntity

@Database(
    entities = [CredentialEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SecureVaultDatabase : RoomDatabase() {

    abstract fun credentialDao(): CredentialDao
}
