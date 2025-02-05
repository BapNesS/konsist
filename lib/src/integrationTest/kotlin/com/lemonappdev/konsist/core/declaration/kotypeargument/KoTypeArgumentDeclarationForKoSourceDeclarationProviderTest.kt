package com.lemonappdev.konsist.core.declaration.kotypeargument

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.type.KoKotlinTypeDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoStarProjectionDeclaration
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class KoTypeArgumentDeclarationForKoSourceDeclarationProviderTest {
    @Test
    fun `not-generic-type-argument-source-declaration`() {
        // given
        val sut =
            getSnippetFile("not-generic-type-argument-source-declaration")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.sourceDeclaration shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.sourceDeclaration?.name shouldBeEqualTo "String"
            it?.hasSourceDeclaration { sourceDeclaration -> sourceDeclaration.isKotlinType } shouldBeEqualTo true
            it?.hasSourceDeclaration { sourceDeclaration -> sourceDeclaration.isExternal } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(String::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(Int::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `generic-type-argument-source-declaration`() {
        // given
        val sut =
            getSnippetFile("generic-type-argument-source-declaration")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.sourceDeclaration shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.sourceDeclaration?.name shouldBeEqualTo "Set"
            it?.hasSourceDeclaration { sourceDeclaration -> sourceDeclaration.isKotlinCollectionType } shouldBeEqualTo true
            it?.hasSourceDeclaration { sourceDeclaration -> sourceDeclaration.isExternal } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(String::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `generic-complex-type-argument-source-declaration`() {
        // given
        val sut =
            getSnippetFile("generic-complex-type-argument-source-declaration")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.sourceDeclaration shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.sourceDeclaration?.name shouldBeEqualTo "Map"
            it?.hasSourceDeclaration { sourceDeclaration -> sourceDeclaration.isKotlinCollectionType } shouldBeEqualTo true
            it?.hasSourceDeclaration { sourceDeclaration -> sourceDeclaration.isExternal } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(String::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `star-projection-type-argument-source-declaration`() {
        // given
        val sut =
            getSnippetFile("star-projection-type-argument-source-declaration")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.sourceDeclaration shouldBeInstanceOf KoStarProjectionDeclaration::class
            it?.sourceDeclaration?.name shouldBeEqualTo "*"
            it?.hasSourceDeclaration { sourceDeclaration -> sourceDeclaration.isStarProjection } shouldBeEqualTo true
            it?.hasSourceDeclaration { sourceDeclaration -> sourceDeclaration.isExternal } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(String::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `out-projection-type-argument-source-declaration`() {
        // given
        val sut =
            getSnippetFile("out-projection-type-argument-source-declaration")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.sourceDeclaration shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.sourceDeclaration?.name shouldBeEqualTo "String"
            it?.hasSourceDeclaration { sourceDeclaration -> sourceDeclaration.isKotlinType } shouldBeEqualTo true
            it?.hasSourceDeclaration { sourceDeclaration -> sourceDeclaration.isExternal } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(String::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(Int::class) shouldBeEqualTo false
        }
    }

    @Test
    fun `in-projection-type-argument-source-declaration`() {
        // given
        val sut =
            getSnippetFile("in-projection-type-argument-source-declaration")
                .properties()
                .first()
                .type
                ?.typeArguments
                ?.firstOrNull()

        // then
        assertSoftly(sut) {
            it?.sourceDeclaration shouldBeInstanceOf KoKotlinTypeDeclaration::class
            it?.sourceDeclaration?.name shouldBeEqualTo "String"
            it?.hasSourceDeclaration { sourceDeclaration -> sourceDeclaration.isKotlinType } shouldBeEqualTo true
            it?.hasSourceDeclaration { sourceDeclaration -> sourceDeclaration.isExternal } shouldBeEqualTo false
            it?.hasSourceDeclarationOf(String::class) shouldBeEqualTo true
            it?.hasSourceDeclarationOf(Int::class) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kotypeargument/snippet/forkosourcedeclarationprovider/",
            fileName,
        )
}
