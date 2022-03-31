import java.math.BigInteger
fun isCyclic(i: String): Boolean {
    // if (i.toIntOrNull() == null) return false
    for (j in 2..i.length) {
        var multiple: String = (i.toBigInteger().times(j.toBigInteger())).toString()
        if (multiple.length < i.length) multiple = "0" + multiple
        for (char in i) {
            val index = multiple.indexOf(char)
            if (index == -1) return false
            multiple = multiple.substring(0, index) + multiple.substringAfter(char)
        }
    }
    return true
}

fun main() {
    println(isCyclic("021276595744680851063829787234042531914893617"))
}