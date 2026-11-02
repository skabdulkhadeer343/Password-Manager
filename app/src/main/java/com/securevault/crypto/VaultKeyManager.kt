
package com.securevault.crypto

interface VaultKeyManager {

    /**
     * Generates a new random vault data encryption key (DEK)
     * and protects it using the master password.
     *
     * Must securely store the wrapped key and salt.
     */
    fun initializeVault(masterPassword: CharArray)

    /**
     * Unlocks the vault by deriving a key from the master password
     * and unwrapping the existing DEK.
     *
     * Throws an exception if the password is incorrect.
     */
    fun unlockVault(masterPassword: CharArray)

    /**
     * Unlocks the vault using the recovery key.
     */
    fun recoverVault(recoveryKey: CharArray)

    /**
     * Changes the master password without re-encrypting
     * all stored credentials.
     */
    fun changeMasterPassword(
        currentPassword: CharArray,
        newPassword: CharArray
    )

    /**
     * Returns whether the vault's DEK is currently unlocked
     * and available for cryptographic operations.
     */
    fun isVaultUnlocked(): Boolean

    /**
     * Clears the unlocked DEK from memory and locks the vault.
     */
    fun lockVault()
}
