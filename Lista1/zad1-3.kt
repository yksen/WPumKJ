fun check(i: Int): String {
    val dividers = arrayOf(3, 5, 7, 11, 13)
    val results = arrayOf("trzy", "piec", "siedem", "jedenascie", "trzynascie")
    var result = ""

    for ((index, divider) in dividers.withIndex()) {
        if (i % divider == 0) {
            result += results[index]
        }
    }

    if (result > "") {
        return result
    } else {
        return "$i"
    }
}

fun main() {
    for (i in 1..100) {
        println(check(i))
    }
}
