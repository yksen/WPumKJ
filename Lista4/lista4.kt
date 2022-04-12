fun isSorted(aa: List<Any>, order: (Any, Any) -> Boolean): Boolean {
    
}

fun suma(a: Array<Int>): Int = (a.filter { it > 0 }).reduce { acc, it -> acc + it }

fun countElements(tab: Array<Array<Char>>): Map<Char, Int> {
    val map = mutableMapOf<Char, Int>()
    tab.flatten().forEach() { if (map.containsKey(it)) map[it] = map[it]!! + 1 else map[it] = 1 }
    return map
}

fun main() {
    println(suma(arrayOf(1, 2, -3, -4, 5)))
    println(
            countElements(
                    arrayOf(arrayOf('a', 'b', 'c'), arrayOf('c', 'd', 'f'), arrayOf('d', 'f', 'g'))
            )
    )
}