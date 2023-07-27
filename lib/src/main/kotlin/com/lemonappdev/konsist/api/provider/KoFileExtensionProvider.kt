package com.lemonappdev.konsist.api.provider

/**
 * An interface representing a Kotlin file that provides access to file extension information.
 */
interface KoFileExtensionProvider : KoBaseProvider {
    /**
     * The extension of the file.
     */
    val extension: String

    /**
     * The name with extension of the file.
     */
    val nameWithExtension: String

    /**
     * Whether file has extension.
     *
     * @param extension The extension to check against. It is a non-null string representing the desired extension.
     * @return `true` if the file's extension matching with the extension, `false` otherwise.
     */
    fun hasExtension(extension: String): Boolean
}
