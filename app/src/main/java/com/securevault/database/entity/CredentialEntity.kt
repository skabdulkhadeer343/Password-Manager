package com.securevault.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "credentials")
data class CredentialEntity(
    @PrimaryKey
    val id: String,

    val title: String,
    val website: String?,

    val usernameEncrypted: ByteArray,
    val passwordEncrypted: ByteArray,
    val notesEncrypted: ByteArray?,

    val category: String,
    val isFavorite: Boolean = false,

    val createdAt: Long,
    val updatedAt: Long
)
