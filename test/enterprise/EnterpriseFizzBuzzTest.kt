package enterprise

import org.testng.annotations.Test

import org.testng.Assert.*

class EnterpriseFizzBuzzTest {

    @Test
    fun fizzBuzz_ofRegularNumber_isTheNumber() {
        assertEquals(fizzBuzz(1), "1")
    }

    @Test
    fun fizzBuzz_of3_isFizz() {
        assertEquals(fizzBuzz(3), "Fizz")
    }

    @Test
    fun fizzBuzz_of5_isBuzz() {
        assertEquals(fizzBuzz(5), "Buzz")
    }

    @Test
    fun fizzBuzz_of15_isFizzBuzz() {
        assertEquals(fizzBuzz(15), "regular.FizzBuzz")
    }
}

fun fizzBuzz(number: Int): String {

    val sayers = listOf(
            FizzBuzzSayer(),
            FizzSayer(),
            BuzzSayer(),
            RegularNumberSayer())

    return sayers
            .filter { it.isApplicable(number) }
            .first()
            .say(number)
}

interface NumberSayer {
    fun isApplicable(number: Int): Boolean
    fun say(number: Int): String
}

class RegularNumberSayer : NumberSayer {
    override fun isApplicable(number: Int): Boolean {
        return true
    }

    override fun say(number: Int): String {
        return "$number"
    }
}

class FizzSayer : NumberSayer {
    override fun isApplicable(number: Int): Boolean {
        return number % 3 == 0
    }

    override fun say(number: Int): String {
        return "Fizz"
    }
}

class BuzzSayer : NumberSayer {
    override fun isApplicable(number: Int): Boolean {
        return number % 5 == 0
    }

    override fun say(number: Int): String {
        return "Buzz"
    }
}

open class CombinedNumberSayer(sayers : List<NumberSayer>) : NumberSayer {

    private val numberSayers: List<NumberSayer> = sayers

    override fun isApplicable(number: Int): Boolean {
        return numberSayers.all { it.isApplicable(number) }
    }

    override fun say(number: Int): String {
        return numberSayers
                .joinTo(buffer=StringBuffer(), separator = "") { it.say(number) }
                .toString()
    }
}

class FizzBuzzSayer : CombinedNumberSayer(listOf(FizzSayer(), BuzzSayer())) {
}