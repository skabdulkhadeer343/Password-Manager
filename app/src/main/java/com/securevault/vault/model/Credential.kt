package com.securevault.vault.model

data class Credential(
    val id: String,
    val title: String,
    val website: String?,
    val username: String,
    val password: String,
    val notes: String?,
    val category: CredentialCategory,
    val createdAt: Long,
    val updatedAt: Long
)
