package com.securevault.crypto

interface CryptoManager {

    /**
     * Encrypts plaintext using AES-GCM.
     * A fresh IV must be generated for every encryption.
     * Returns IV + ciphertext + authentication tag.
     */
    fun encrypt(plaintext: String): ByteArray

    /**
     * Decrypts data produced by encrypt().
     * Must throw an exception if authentication fails
     * or ciphertext is corrupted.
     */
    fun decrypt(ciphertext: ByteArray): String
}
