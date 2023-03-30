package com.lemon.konsist.core.kocomplexdeclaration

import SampleClass
import com.lemon.konsist.TestSnippetProvider
import com.lemon.konsist.core.const.Modifier.PRIVATE
import com.lemon.konsist.core.const.Modifier.PUBLIC
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoComplexDeclarationForInterfaceTest {
    @Test
    fun `interface-with-class includeNested true`() {
        // given
        val sut = getSut("interface-with-class")
            .interfaces()
            .first()

        // then
        sut
            .classes(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass")
    }

    @Test
    fun `interface-with-class includeNested false`() {
        // given
        val sut = getSut("interface-with-class")
            .interfaces()
            .first()

        // then
        sut
            .classes(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass")
    }

    @Test
    fun `interface-with-interface includeNested true`() {
        // given
        val sut = getSut("interface-with-interface")
            .interfaces()
            .first()

        // then
        sut
            .interfaces(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedInterface")
    }

    @Test
    fun `interface-with-interface includeNested false`() {
        // given
        val sut = getSut("interface-with-interface")
            .interfaces()
            .first()

        // then
        sut
            .interfaces(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedInterface")
    }

    @Test
    fun `interface-with-object includeNested true`() {
        // given
        val sut = getSut("interface-with-object")
            .interfaces()
            .first()

        // then
        sut
            .objects(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject")
    }

    @Test
    fun `interface-with-object includeNested false`() {
        // given
        val sut = getSut("interface-with-object")
            .interfaces()
            .first()

        // then
        sut
            .objects(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject")
    }

    @Test
    fun `interface-with-property includeNested true`() {
        // given
        val sut = getSut("interface-with-property")
            .interfaces()
            .first()

        // then
        sut
            .properties(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty")
    }

    @Test
    fun `interface-with-property includeNested false`() {
        // given
        val sut = getSut("interface-with-property")
            .interfaces()
            .first()

        // then
        sut
            .properties(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty")
    }

    @Test
    fun `interface-with-function includeNested true`() {
        // given
        val sut = getSut("interface-with-function")
            .interfaces()
            .first()

        // then
        sut
            .functions(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedFunction")
    }

    @Test
    fun `interface-with-function includeNested false`() {
        // given
        val sut = getSut("interface-with-function")
            .interfaces()
            .first()

        // then
        sut
            .functions(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedFunction")
    }

    @Test
    fun `interface-with-all-declarations includeNested true`() {
        // given
        val sut = getSut("interface-with-all-declarations")
            .interfaces()
            .first()

        // then
        val expected = listOf(
            "SampleNestedClass",
            "SampleNestedInterface",
            "SampleNestedObject",
            "sampleNestedProperty",
            "sampleNestedFunction",
        )

        sut
            .declarations(includeNested = true)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `interface-with-all-declarations includeNested false`() {
        // given
        val sut = getSut("interface-with-all-declarations")
            .interfaces()
            .first()

        // then
        val expected = listOf(
            "SampleNestedClass",
            "SampleNestedInterface",
            "SampleNestedObject",
            "sampleNestedProperty",
            "sampleNestedFunction",
        )

        sut
            .declarations(includeNested = false)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `interface-with-nested-classes includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-classes")
            .interfaces()
            .first()

        // then
        sut
            .classes(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass1", "SampleNestedClass2", "SampleNestedClass3")
    }

    @Test
    fun `interface-with-nested-classes includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-classes")
            .interfaces()
            .first()

        // then
        sut
            .classes(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedClass1")
    }

    @Test
    fun `interface-with-nested-interfaces includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-interfaces")
            .interfaces()
            .first()

        // then
        sut
            .interfaces(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedInterface3", "SampleNestedInterface1", "SampleNestedInterface2")
    }

    @Test
    fun `interface-with-nested-interfaces includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-interfaces")
            .interfaces()
            .first()

        // then
        sut
            .interfaces(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedInterface1")
    }

    @Test
    fun `interface-with-nested-objects includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-objects")
            .interfaces()
            .first()

        // then
        sut
            .objects(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject3", "SampleNestedObject1", "SampleNestedObject2")
    }

    @Test
    fun `interface-with-nested-objects includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-objects")
            .interfaces()
            .first()

        // then
        sut
            .objects(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("SampleNestedObject1")
    }

    @Test
    fun `interface-with-nested-properties includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-properties")
            .interfaces()
            .first()

        // then
        sut
            .properties(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty2", "sampleNestedProperty1")
    }

    @Test
    fun `interface-with-nested-properties includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-properties")
            .interfaces()
            .first()

        // then
        sut
            .properties(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedProperty1")
    }

    @Test
    fun `interface-with-nested-functions includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-functions")
            .interfaces()
            .first()

        // then
        sut
            .functions(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedFunction1", "sampleNestedFunction2", "sampleLocalFunction")
    }

    @Test
    fun `interface-with-nested-functions includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-functions")
            .interfaces()
            .first()

        // then
        sut
            .functions(includeNested = false)
            .map { it.name } shouldBeEqualTo listOf("sampleNestedFunction1")
    }

    @Test
    fun `interface-with-nested-declarations includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-declarations")
            .interfaces()
            .first()

        // then
        val expected = listOf(
            "SampleNestedClass",
            "SampleNestedObject",
            "SampleNestedInterface",
            "sampleNestedFunction",
            "SampleNestedObject",
            "sampleNestedProperty",
            "sampleNestedProperty",
            "sampleNestedFunction",
        )

        sut
            .declarations(includeNested = true)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `interface-with-nested-declarations includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-declarations")
            .interfaces()
            .first()

        // then
        val expected =
            listOf("SampleNestedClass", "SampleNestedInterface", "SampleNestedObject", "sampleNestedProperty", "sampleNestedFunction")

        sut
            .declarations(includeNested = false)
            .map { it.name } shouldBeEqualTo expected
    }

    @Test
    fun `interface-with-class-which-not-represents-type`() {
        // given
        val sut = getSut("interface-with-class-which-not-represents-type")
            .interfaces()
            .first()

        // then
        sut.representsType(SampleClass::class) shouldBeEqualTo false
    }

    @Test
    fun `interface-with-nested-functions-with-modifiers includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-functions-with-modifiers")
            .interfaces()
            .first()

        // then
        sut.apply {
            hasFunction("sampleNestedFunction1", PRIVATE, includeNested = false) shouldBeEqualTo true
            hasFunction("sampleNestedFunction1", PUBLIC, includeNested = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-nested-functions-with-modifiers includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-functions-with-modifiers")
            .interfaces()
            .first()

        // then
        sut.apply {
            hasFunction("sampleNestedFunction2", PRIVATE, includeNested = true) shouldBeEqualTo true
            hasFunction("sampleNestedFunction2", PUBLIC, includeNested = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-nested-properties-with-modifiers includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-properties-with-modifiers")
            .interfaces()
            .first()

        // then
        sut.apply {
            hasProperty("sampleNestedProperty1", PRIVATE, includeNested = false) shouldBeEqualTo true
            hasProperty("sampleNestedProperty1", PUBLIC, includeNested = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-nested-properties-with-modifiers includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-properties-with-modifiers")
            .interfaces()
            .first()

        // then
        sut.apply {
            hasProperty("sampleNestedProperty2", PRIVATE, includeNested = true) shouldBeEqualTo true
            hasProperty("sampleNestedProperty2", PUBLIC, includeNested = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-nested-classes-with-modifiers includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-classes-with-modifiers")
            .interfaces()
            .first()

        // then
        sut.apply {
            hasClass("SampleNestedClass1", PRIVATE, includeNested = false) shouldBeEqualTo true
            hasClass("SampleNestedClass1", PUBLIC, includeNested = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-nested-classes-with-modifiers includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-classes-with-modifiers")
            .interfaces()
            .first()

        // then
        sut.apply {
            hasClass("SampleNestedClass2", PRIVATE, includeNested = true) shouldBeEqualTo true
            hasClass("SampleNestedClass2", PUBLIC, includeNested = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-nested-interfaces-with-modifiers includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-interfaces-with-modifiers")
            .interfaces()
            .first()

        // then
        sut.apply {
            hasInterface("SampleNestedInterface1", PRIVATE, includeNested = false) shouldBeEqualTo true
            hasInterface("SampleNestedInterface1", PUBLIC, includeNested = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-nested-interfaces-with-modifiers includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-interfaces-with-modifiers")
            .interfaces()
            .first()

        // then
        sut.apply {
            hasInterface("SampleNestedInterface2", PRIVATE, includeNested = true) shouldBeEqualTo true
            hasInterface("SampleNestedInterface2", PUBLIC, includeNested = true) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-nested-objects-with-modifiers includeNested false`() {
        // given
        val sut = getSut("interface-with-nested-objects-with-modifiers")
            .interfaces()
            .first()

        // then
        sut.apply {
            hasObject("SampleNestedObject1", PRIVATE, includeNested = false) shouldBeEqualTo true
            hasObject("SampleNestedObject1", PUBLIC, includeNested = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-with-nested-objects-with-modifiers includeNested true`() {
        // given
        val sut = getSut("interface-with-nested-objects-with-modifiers")
            .interfaces()
            .first()

        // then
        sut.apply {
            hasObject("SampleNestedObject2", PRIVATE, includeNested = true) shouldBeEqualTo true
            hasObject("SampleNestedObject2", PUBLIC, includeNested = true) shouldBeEqualTo false
        }
    }

    private fun getSut(name: String) = TestSnippetProvider.getSnippetKoScope("core/kocomplexdeclaration/snippet/forinterface/$name.kt.txt")
}
