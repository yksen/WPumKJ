val zad1: (String, Int) -> String = { s: String, i: Int -> s.repeat(i) }

val f: (String) -> String = { it + "!" }

fun fib(i: Int): Int {
    tailrec fun fib(i: Int, a: Int = 0, b: Int = 1): Int = if (i == 0) a else fib(i - 1, b, a + b)
    return fib(i)
}

fun log2(i: Int): Int {
    tailrec fun log2(i: Int, a: Int = 0): Int = if (i == 1) a else log2(i / 2, a + 1)
    return log2(i)
}

fun formatResult(name: String, n: Int, f: (Int) -> Int): String {
    return "$name($n) = ${f(n)}"
}

val <T> List<T>.tail: List<T>
    get() = drop(1)
val <T> List<T>.head: T
    get() = first()

fun <A> isSorted(aa: List<A>, order: (A, A) -> Boolean): Boolean {
    fun isSorted(aa: List<A>, order: (A, A) -> Boolean): Boolean =
            when {
                aa.isEmpty() -> true
                aa.tail.isEmpty() -> true
                else -> order(aa.head, aa.tail.head) && isSorted(aa.tail, order)
            }
    return isSorted(aa, order)
}

fun suma(a: Array<Int>): Int = (a.filter { it > 0 }).reduce { acc, it -> acc + it }

fun countElements(tab: Array<Array<Char>>): Map<Char, Int> {
    val map = mutableMapOf<Char, Int>()
    tab.flatten().forEach() { if (map.containsKey(it)) map[it] = map[it]!! + 1 else map[it] = 1 }
    return map
}

fun main() {
    println(zad1("a", 3))

    println(f("a"))

    println(fib(20))

    println(log2(16))

    println(formatResult("fib", 20, ::fib))

    println(listOf(1, 2, 3, 4, 5).tail)
    println(listOf(1, 2, 3, 4, 5).head)

    println(isSorted(listOf(1, 2, 3, 4), { i: Int, j: Int -> i < j }))
    println(isSorted(listOf(1, 1, 1, 1), { i: Int, j: Int -> i == j }))
    println(
            isSorted(
                    listOf("ahyyhh", "bkjn", "cnn", "duu"),
                    { i: String, j: String -> i.first() < j.first() }
            )
    )

    println(suma(arrayOf(1, -4, 12, 0, -3, 29, -150)))

    println(
            countElements(
                    arrayOf(arrayOf('a', 'b', 'c'), arrayOf('c', 'd', 'f'), arrayOf('d', 'f', 'g'))
            )
    )
}
