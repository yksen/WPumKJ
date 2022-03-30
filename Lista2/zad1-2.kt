fun convertToRoman(n: Int): String {
    val roman = arrayOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
    val arabic = arrayOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)

    var total = n
    var result = ""
    
    var i = 0
    while (total > 0) {
        while (total >= arabic[i]) {
            result += roman[i]
            total -= arabic[i]
        }
        i++
    }
    return result
}

fun convertFromRoman(s: String): Int {
    val roman = mapOf("M" to 1000, "D" to 500, "C" to 100, "L" to 50, "X" to 10, "V" to 5, "I" to 1)

    var result = 0

    for (i in 0 until s.length - 1) {
        val currNum = roman[s[i].toString()]
        val nextNum = roman[s[i + 1].toString()]
        if (currNum!! < nextNum!!) result -= currNum else result += currNum
    }
    result += roman[s.last().toString()]!!
    return result
}

fun main() {
    for (i in 1..5000) {
        print(i.toString() + "\t")
        print(convertToRoman(i) + "\t")
        println(convertFromRoman(convertToRoman(i)))
    }
}