package com.lemonappdev.konsist.api.provider

import com.lemonappdev.konsist.api.container.KoFile

/**
 * An interface representing a Kotlin declaration that provides access to the file containing the declaration.
 */
interface KoContainingFileProvider : KoBaseProvider {
    /**
     * File containing the declaration.
     */
    val containingFile: KoFile
}
