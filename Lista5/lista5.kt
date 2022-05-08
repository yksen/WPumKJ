fun oddIndexesSquared(numbers: List<Number>): List<Number> {
    return numbers.filterIndexed { index, value -> index % 2 == 1 && value.toDouble() > 0 }.map {
        it.toDouble() * it.toDouble()
    }
}

fun groupEvenLengthWordsSortedByFirstLetter(words: List<String>): List<List<String>> {
    return words.filter { it.length % 2 == 0 }.sortedBy { it[0] }.groupBy { it[0] }.map { it.value }
}

fun permutations(vararg params: Int): List<List<Int>> {
    var input: List<Int> = params.toList()
    if (input.size == 1) return listOf(input)

    val results = mutableListOf<List<Int>>()
    val toInsert = input[0]

    for (permutation in permutations(*input.drop(1).toIntArray())) {
        for (i in 0..permutation.size) {
            val newPermutation = permutation.toMutableList()
            newPermutation.add(i, toInsert)
            results.add(newPermutation)
        }
    }
    return results
}

fun check(N: Int, listOfNumbers: List<Int>): Int {

    var start = 0
    var end = start + N - 1
    var number = listOfNumbers[end + 1]
    var isValid = true

    while (end < listOfNumbers.size - 1 && isValid) {
        number = listOfNumbers[end + 1]
        isValid = false

        for (i in start..end) {
            for (j in i..end) {
                if (listOfNumbers[i] + listOfNumbers[j] == number) {
                    isValid = true
                    break
                }
            }
        }

        ++start
        end = start + N - 1
    }

    return number
}

fun main() {
    println(oddIndexesSquared(listOf(1, 2, 3.5, 5, -6, 1, 1)))

    println(
            groupEvenLengthWordsSortedByFirstLetter(
                    listOf("cherry", "blueberry", "citrus", "apple", "apricot", "banana", "coconut")
            )
    )

    println(permutations(1, 2, 3))

    println(
            check(
                    5,
                    listOf(
                            35,
                            25,
                            15,
                            25,
                            47,
                            40,
                            62,
                            55,
                            65,
                            95,
                            102,
                            117,
                            150,
                            182,
                            127,
                            219,
                            299,
                            277,
                            309,
                            576
                    )
            )
    )
}
