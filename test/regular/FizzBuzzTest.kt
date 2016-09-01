package regular

import org.testng.annotations.Test

import org.testng.Assert.*

class FizzBuzzTest {

    @Test
    fun fizzBuzz_of1_is1() {
        assertEquals("1", fizzBuzz(1))
    }

    @Test
    fun fizzBuzz_of2_is2() {
        assertEquals("2", fizzBuzz(2))
    }

    @Test
    fun fizzBuzz_of3_isFizz() {
        assertEquals("Fizz", fizzBuzz(3))
    }

    @Test
    fun fizzBuzz_of6_isFizz() {
        assertEquals("Fizz", fizzBuzz(6))
    }

    @Test
    fun fizzBuzz_of5_isBuzz() {
        assertEquals("Buzz", fizzBuzz(5))
    }

    @Test
    fun fizzBuzz_of15_isBuzz() {
        assertEquals("regular.FizzBuzz", fizzBuzz(15))
    }

    @Test
    fun shouldRecogniseThrees() {
        val fizzBuzz = FizzBuzz()
        assertEquals(fizzBuzz.threeable(3), true)
        assertFalse(fizzBuzz.threeable(4))
    }

    @Test
    fun shouldRecogniseFives() {
        val fizzBuzz = FizzBuzz()
        assertEquals(fizzBuzz.fiveable(5), true)
        assertFalse(fizzBuzz.fiveable(4))
    }

    @Test
    fun shouldRecogniseFifteens() {
        val fizzBuzz = FizzBuzz()
        assertEquals(fizzBuzz.fifteenable(15), true)
        assertFalse(fizzBuzz.fifteenable(4))
    }


}

interface Three {
    fun threeable(number: Int): Boolean {
        return number % 3 == 0
    }
}

interface Five {
    fun fiveable(number: Int): Boolean {
        return number % 5 == 0
    }
}

interface Fifteen {
    fun fifteenable(number: Int): Boolean {
        return number % 15 == 0
    }
}

class FizzBuzz : Three, Five, Fifteen {
    fun fizzBuzz(number: Int): String {
        return when {
            fifteenable(number) -> "regular.FizzBuzz"
            fiveable(number) -> "Buzz"
            threeable(number) -> "Fizz"
            else -> "$number"
        }
    }
}

fun fizzBuzz(number: Int): String {
    return FizzBuzz().fizzBuzz(number)
}
