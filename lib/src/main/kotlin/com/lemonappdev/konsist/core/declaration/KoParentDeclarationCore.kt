package com.lemonappdev.konsist.core.declaration

import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.KoBaseDeclaration
import com.lemonappdev.konsist.api.declaration.KoParentDeclaration
import com.lemonappdev.konsist.api.provider.KoDeclarationCastProvider
import com.lemonappdev.konsist.core.cache.KoDeclarationCache
import com.lemonappdev.konsist.core.model.getClass
import com.lemonappdev.konsist.core.model.getInterface
import com.lemonappdev.konsist.core.model.getTypeAlias
import com.lemonappdev.konsist.core.provider.KoAnnotationProviderCore
import com.lemonappdev.konsist.core.provider.KoArgumentProviderCore
import com.lemonappdev.konsist.core.provider.KoBaseProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoContainingFileProviderCore
import com.lemonappdev.konsist.core.provider.KoInterfaceDelegateProviderCore
import com.lemonappdev.konsist.core.provider.KoLocationProviderCore
import com.lemonappdev.konsist.core.provider.KoModuleProviderCore
import com.lemonappdev.konsist.core.provider.KoNameProviderCore
import com.lemonappdev.konsist.core.provider.KoPathProviderCore
import com.lemonappdev.konsist.core.provider.KoResideInPackageProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceDeclarationProviderCore
import com.lemonappdev.konsist.core.provider.KoSourceSetProviderCore
import com.lemonappdev.konsist.core.provider.KoTextProviderCore
import com.lemonappdev.konsist.core.provider.KoTypeArgumentProviderCore
import com.lemonappdev.konsist.core.provider.packagee.KoPackageDeclarationProviderCore
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtConstructorCalleeExpression
import org.jetbrains.kotlin.psi.KtDeclarationModifierList
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtNameReferenceExpression
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry
import org.jetbrains.kotlin.psi.KtTypeProjection
import org.jetbrains.kotlin.psi.KtTypeReference
import org.jetbrains.kotlin.psi.KtUserType
import org.jetbrains.kotlin.psi.KtValueArgument
import org.jetbrains.kotlin.psi.KtValueArgumentList

internal class KoParentDeclarationCore(
    override val ktSuperTypeListEntry: KtSuperTypeListEntry,
    override val containingDeclaration: KoBaseDeclaration,
) : KoParentDeclaration,
    KoBaseProviderCore,
    KoNameProviderCore,
    KoPackageDeclarationProviderCore,
    KoResideInPackageProviderCore,
    KoSourceDeclarationProviderCore,
    KoTypeArgumentProviderCore,
    KoTextProviderCore,
    KoContainingFileProviderCore,
    KoContainingDeclarationProviderCore,
    KoPathProviderCore,
    KoLocationProviderCore,
    KoAnnotationProviderCore,
    KoModuleProviderCore,
    KoSourceSetProviderCore,
    KoArgumentProviderCore,
    KoInterfaceDelegateProviderCore {
    override val psiElement: PsiElement by lazy { ktSuperTypeListEntry }

    override val ktElement: KtElement by lazy { ktSuperTypeListEntry }

    override val ktTypeReference: KtTypeReference? by lazy { null }

    override val ktNameReferenceExpression: KtNameReferenceExpression? by lazy { null }

    override val ktTypeProjection: KtTypeProjection? by lazy { null }

    override val ktUserType: KtUserType? by lazy { ktSuperTypeListEntry.typeAsUserType }

    override val ktAnnotationEntries: List<KtAnnotationEntry>? by lazy {
        val children = ktSuperTypeListEntry.children

        val targetChildren =
            children
                .filterIsInstance<KtConstructorCalleeExpression>()
                .firstOrNull()
                ?.children
                ?: children

        targetChildren
            .firstOrNull { it is KtTypeReference }
            ?.children
            ?.firstOrNull { it is KtDeclarationModifierList }
            ?.children
            ?.filterIsInstance<KtAnnotationEntry>()
    }

    override val sourceDeclaration: KoDeclarationCastProvider? by lazy {
        val name =
            ktSuperTypeListEntry
                .text
                .substringBefore(" ")
                .substringBefore("(")
                .substringBefore("<")

        val innerName = if (name.contains(".")) name.substringBeforeLast(".") else name
        val outerName = if (name.contains(".")) name.substringAfterLast(".") else name

        val import =
            containingFile
                .imports
                .firstOrNull { import ->
                    if (import.alias != null) {
                        import.alias?.name == innerName
                    } else {
                        import.name.substringAfterLast(".") == outerName
                    }
                }

        val fullyQualifiedName =
            import?.name
                ?: "${containingFile.packagee?.name?.let { "$it." } ?: ""}$name"

        val isAlias = import?.alias != null

        (
            import?.alias
                ?: getClass(outerName, fullyQualifiedName, isAlias, containingFile)
                ?: getInterface(outerName, fullyQualifiedName, isAlias, containingFile)
                ?: getTypeAlias(outerName, fullyQualifiedName, containingFile)
                ?: KoExternalDeclarationCore.getInstance(outerName, ktSuperTypeListEntry)
        )
            as? KoDeclarationCastProvider
    }

    override val name: String by lazy {
        val children = ktElement.children

        children
            .filterIsInstance<KtTypeReference>()
            .firstOrNull()
            ?.text
            ?: children
                .flatMap { it.children.toList() }
                .filterIsInstance<KtTypeReference>()
                .firstOrNull()
                ?.text
            ?: text
    }

    override val arguments: List<KoArgumentDeclaration> by lazy {
        ktSuperTypeListEntry
            .children
            .filterIsInstance<KtValueArgumentList>()
            .firstOrNull()
            ?.children
            ?.filterIsInstance<KtValueArgument>()
            ?.map { KoArgumentDeclarationCore.getInstance(it, this) }
            .orEmpty()
    }

    override fun toString(): String = text

    internal companion object {
        private val cache: KoDeclarationCache<KoParentDeclaration> = KoDeclarationCache()

        internal fun getInstance(
            ktSuperTypeListEntry: KtSuperTypeListEntry,
            containingDeclaration: KoBaseDeclaration,
        ): KoParentDeclaration =
            cache.getOrCreateInstance(ktSuperTypeListEntry, containingDeclaration) {
                KoParentDeclarationCore(ktSuperTypeListEntry, containingDeclaration)
            }
    }
}
