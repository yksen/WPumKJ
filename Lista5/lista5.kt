fun oddIndexesSquared(numbers: List<Number>): List<Number> {
    return numbers
        .filterIndexed { index, value -> index % 2 == 1 && value.toDouble() > 0 }
        .map { it.toDouble() * it.toDouble()
    }
}

fun sortEvenLengthWordsByFirstLetter(words: List<String>): List<List<String>> {
    return words.filter { it.length % 2 == 0 }.sortedBy { it[0] }.groupBy { it[0] }.map { it.value }
}

fun permutations(vararg params: Int): List<List<Int>> {

    return listOf()
}



fun main() {
    println(oddIndexesSquared(listOf(1, 2, 3.5, 5, -6, 1, 1)))

    println(
            sortEvenLengthWordsByFirstLetter(
                    listOf("cherry", "blueberry", "citrus", "apple", "apricot", "banana", "coconut")
            )
    )

    println(permutations(1, 2, 3))
}
