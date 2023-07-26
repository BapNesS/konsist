package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoParentClassProvider
import com.lemonappdev.konsist.core.declaration.KoParentDeclarationImpl
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleClass1
import com.lemonappdev.konsist.testdata.SampleClass2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoParentClassProviderSequenceExtTest {
    @Test
    fun `withParentClass() returns declaration with any parent declaration`() {
        // given
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClass() } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClass() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentClass()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentClass() returns declaration without any parent declaration`() {
        // given
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClass() } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClass() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentClass()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParentClass(name) returns declaration with given parent declaration name`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClass(name) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClass(name) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentClass(name)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParentClass(name) returns declarations with one of given parent declaration names`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClass(name1) } returns true
            every { hasParentClass(name2) } returns false
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClass(name1) } returns false
            every { hasParentClass(name2) } returns true
        }
        val declaration3: KoParentClassProvider = mockk {
            every { hasParentClass(name1) } returns false
            every { hasParentClass(name2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withParentClass(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutParentClass(name) returns declaration without given parent declaration name`() {
        // given
        val name = "SampleName"
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClass(name) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClass(name) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentClass(name)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParentClass(name) returns declaration without any of given parent declaration names`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClass(name1) } returns true
            every { hasParentClass(name2) } returns false
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClass(name1) } returns false
            every { hasParentClass(name2) } returns true
        }
        val declaration3: KoParentClassProvider = mockk {
            every { hasParentClass(name1) } returns false
            every { hasParentClass(name2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutParentClass(name1, name2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withParentClassOf() returns declaration with SampleClass parent declaration`() {
        // given
        val name1 = "SampleClass"
        val name2 = "OtherClass"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val declaration1: KoParentClassProvider = mockk {
            every { parentClass } returns parent1
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }
        val declaration2: KoParentClassProvider = mockk {
            every { parentClass } returns parent2
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentClassOf<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParentClassOf() returns declaration without SampleClass parent declaration`() {
        // given
        val name1 = "SampleClass"
        val name2 = "OtherClass"
        val parent1: KoParentDeclarationImpl = mockk {
            every { name } returns name1
        }
        val declaration1: KoParentClassProvider = mockk {
            every { parentClass } returns parent1
        }
        val parent2: KoParentDeclarationImpl = mockk {
            every { name } returns name2
        }
        val declaration2: KoParentClassProvider = mockk {
            every { parentClass } returns parent2
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentClassOf<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParentClassOf(KClass) returns declaration with given parent declaration`() {
        // given
        val name1 = "SampleClass1"
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClass(name1) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClass(name1) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withParentClassOf(SampleClass1::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParentClassOf(KClass) returns declarations with one of given parent declarations`() {
        // given
        val name1 = "SampleClass1"
        val name2 = "SampleClass2"
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClass(name1) } returns true
            every { hasParentClass(name2) } returns false
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClass(name1) } returns false
            every { hasParentClass(name2) } returns true
        }
        val declaration3: KoParentClassProvider = mockk {
            every { hasParentClass(name1) } returns false
            every { hasParentClass(name2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withParentClassOf(SampleClass1::class, SampleClass2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutParentClassOf(KClass) returns declaration without given parent declaration`() {
        // given
        val name1 = "SampleClass1"
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClass(name1) } returns true
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClass(name1) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParentClassOf(SampleClass1::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParentClassOf(KClass) returns declaration without any of given parent declarations`() {
        // given
        val name1 = "SampleClass1"
        val name2 = "SampleClass2"
        val declaration1: KoParentClassProvider = mockk {
            every { hasParentClass(name1) } returns true
            every { hasParentClass(name2) } returns false
        }
        val declaration2: KoParentClassProvider = mockk {
            every { hasParentClass(name1) } returns false
            every { hasParentClass(name2) } returns true
        }
        val declaration3: KoParentClassProvider = mockk {
            every { hasParentClass(name1) } returns false
            every { hasParentClass(name2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutParentClassOf(SampleClass1::class, SampleClass2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration3)
    }
}
