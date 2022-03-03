fun missingNumber(tab: IntArray): Int {
    tab.sort()
    var number: Int = 0;
    while (number < tab.size) {
        if (number == tab[number])
            number++
        else
            break
    }
    return number
}

fun main() {
    val tab = intArrayOf(2, 4, 1, 3, 6, 0)
    println(missingNumber(tab))
}