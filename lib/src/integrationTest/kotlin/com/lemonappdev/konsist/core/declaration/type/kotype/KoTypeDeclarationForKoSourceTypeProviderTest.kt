package com.lemonappdev.konsist.core.declaration.type.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeDeclarationForKoSourceTypeProviderTest {
    @Test
    fun `nullable-class-type`() {
        // given
        val sut =
            getSnippetFile("nullable-class-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "SampleType?"
            it?.bareSourceType shouldBeEqualTo "SampleType"
        }
    }

    @Test
    fun `not-nullable-class-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-class-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "SampleType"
            it?.bareSourceType shouldBeEqualTo "SampleType"
        }
    }

    @Test
    fun `nullable-interface-type`() {
        // given
        val sut =
            getSnippetFile("nullable-interface-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "SampleInterface?"
            it?.bareSourceType shouldBeEqualTo "SampleInterface"
        }
    }

    @Test
    fun `not-nullable-interface-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-interface-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "SampleInterface"
            it?.bareSourceType shouldBeEqualTo "SampleInterface"
        }
    }

    @Test
    fun `nullable-object-type`() {
        // given
        val sut =
            getSnippetFile("nullable-object-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "SampleObject?"
            it?.bareSourceType shouldBeEqualTo "SampleObject"
        }
    }

    @Test
    fun `not-nullable-object-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-object-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "SampleObject"
            it?.bareSourceType shouldBeEqualTo "SampleObject"
        }
    }

    @Test
    fun `nullable-typealias-type`() {
        // given
        val sut =
            getSnippetFile("nullable-typealias-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "SampleTypeAlias?"
            it?.bareSourceType shouldBeEqualTo "() -> Unit"
        }
    }

    @Test
    fun `not-nullable-typealias-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-typealias-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "SampleTypeAlias"
            it?.bareSourceType shouldBeEqualTo "() -> Unit"
        }
    }

    @Test
    fun `nullable-type-parameter`() {
        // given
        val sut =
            getSnippetFile("nullable-type-parameter")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "TestType?"
            it?.bareSourceType shouldBeEqualTo "TestType"
        }
    }

    @Test
    fun `not-nullable-type-parameter`() {
        // given
        val sut =
            getSnippetFile("not-nullable-type-parameter")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()
                ?.type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "TestType"
            it?.bareSourceType shouldBeEqualTo "TestType"
        }
    }

    @Test
    fun `not-nullable-kotlin-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-kotlin-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "String"
            it?.bareSourceType shouldBeEqualTo "String"
        }
    }

    @Test
    fun `nullable-kotlin-type`() {
        // given
        val sut =
            getSnippetFile("nullable-kotlin-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "String?"
            it?.bareSourceType shouldBeEqualTo "String"
        }
    }

    @Test
    fun `not-nullable-generic-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-generic-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "List<SampleType>"
            it?.bareSourceType shouldBeEqualTo "List"
        }
    }

    @Test
    fun `not-nullable-generic-type-with-nullable-type-argument`() {
        // given
        val sut =
            getSnippetFile("not-nullable-generic-type-with-nullable-type-argument")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "List<SampleType?>"
            it?.bareSourceType shouldBeEqualTo "List"
        }
    }

    @Test
    fun `nullable-generic-type`() {
        // given
        val sut =
            getSnippetFile("nullable-generic-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "List<SampleType>?"
            it?.bareSourceType shouldBeEqualTo "List"
        }
    }

    @Test
    fun `nullable-generic-type-with-nullable-type-argument`() {
        // given
        val sut =
            getSnippetFile("nullable-generic-type-with-nullable-type-argument")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "List<SampleType?>?"
            it?.bareSourceType shouldBeEqualTo "List"
        }
    }

    @Test
    fun `not-nullable-import-alias-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-import-alias-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "SampleType"
            it?.bareSourceType shouldBeEqualTo "SampleType"
        }
    }

    @Test
    fun `nullable-import-alias-type`() {
        // given
        val sut =
            getSnippetFile("nullable-import-alias-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "SampleType"
            it?.bareSourceType shouldBeEqualTo "SampleType"
        }
    }

    @Test
    fun `not-nullable-function-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-function-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "() -> Unit"
            it?.bareSourceType shouldBeEqualTo "() -> Unit"
        }
    }

    @Test
    fun `nullable-function-type`() {
        // given
        val sut =
            getSnippetFile("nullable-function-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "(() -> Unit)?"
            it?.bareSourceType shouldBeEqualTo "() -> Unit"
        }
    }

    @Test
    fun `not-nullable-external-type`() {
        // given
        val sut =
            getSnippetFile("not-nullable-external-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "SampleExternalClass"
            it?.bareSourceType shouldBeEqualTo "SampleExternalClass"
        }
    }

    @Test
    fun `nullable-external-type`() {
        // given
        val sut =
            getSnippetFile("nullable-external-type")
                .properties()
                .first()
                .type

        // then
        assertSoftly(sut) {
            it?.sourceType shouldBeEqualTo "SampleExternalClass?"
            it?.bareSourceType shouldBeEqualTo "SampleExternalClass"
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/type/kotype/snippet/forkosourcetypeprovider/",
            fileName,
        )
}
