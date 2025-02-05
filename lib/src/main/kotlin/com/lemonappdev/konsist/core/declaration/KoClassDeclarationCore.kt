package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.declaration.combined.KoClassAndInterfaceDeclarationCore
import com.lemonappdev.konsist.core.declaration.combined.KoClassAndObjectDeclarationCore
import com.lemonappdev.konsist.core.provider.KoConstructorProviderCore
import com.lemonappdev.konsist.core.provider.KoEnumConstantProviderCore
import com.lemonappdev.konsist.core.provider.KoInitBlockProviderCore
import com.lemonappdev.konsist.core.provider.KoParentClassProviderCore
import com.lemonappdev.konsist.core.provider.KoPrimaryConstructorProviderCore
import com.lemonappdev.konsist.core.provider.KoSecondaryConstructorsProviderCore
import com.lemonappdev.konsist.core.provider.KoTestClassProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoAbstractModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoAnnotationModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoDataModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoEnumModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoFinalModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoInnerModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoOpenModifierProviderCore
import com.lemonappdev.konsist.core.provider.modifier.KoValueModifierProviderCore
import com.lemonappdev.konsist.core.provider.util.KoDeclarationProviderCoreUtil
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtClassOrObject
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtModifierListOwner
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner

internal class KoClassDeclarationCore private constructor(
    override val ktClass: KtClass,
    override val containingDeclaration: KoBaseDeclaration,
) : KoClassDeclaration,
    KoClassAndInterfaceDeclarationCore,
    KoClassAndObjectDeclarationCore,
    KoAbstractModifierProviderCore,
    KoAnnotationModifierProviderCore,
    KoConstructorProviderCore,
    KoDataModifierProviderCore,
    KoEnumConstantProviderCore,
    KoEnumModifierProviderCore,
    KoFinalModifierProviderCore,
    KoInitBlockProviderCore,
    KoInnerModifierProviderCore,
    KoOpenModifierProviderCore,
    KoParentClassProviderCore,
    KoPrimaryConstructorProviderCore,
    KoSecondaryConstructorsProviderCore,
    KoTestClassProviderCore,
    KoValueModifierProviderCore {
    override val ktModifierListOwner: KtModifierListOwner by lazy { ktClass }

    override val ktTypeParameterListOwner: KtTypeParameterListOwner by lazy { ktClass }

    override val ktAnnotationEntries: List<KtAnnotationEntry>? by lazy { ktClass.annotationEntries }

    override val psiElement: PsiElement by lazy { ktClass }

    override val ktElement: KtElement by lazy { ktClass }

    override val ktClassOrObject: KtClassOrObject by lazy { ktClass }

    override fun declarations(
        includeNested: Boolean,
        includeLocal: Boolean,
    ): List<KoBaseDeclaration> =
        KoDeclarationProviderCoreUtil
            .getKoDeclarations(ktClass, includeNested, includeLocal, this)

    override fun toString(): String = name

    internal companion object {
        private val cache: KoDeclarationCache<KoClassDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktClass: KtClass,
            containingDeclaration: KoBaseDeclaration,
        ): KoClassDeclaration =
            cache.getOrCreateInstance(ktClass, containingDeclaration) {
                KoClassDeclarationCore(ktClass, containingDeclaration)
            }
    }
}
