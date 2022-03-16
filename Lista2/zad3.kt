fun isCyclic(i: String): Boolean {
    for (j in 2..i.length) {
        var multiple : String = (j * i.toInt()).toString()
        for (char in i) {
            val index = multiple.indexOf(char)
            if (index == -1) return false
            multiple = multiple.substring(0, index) + multiple.substringAfter(char)
        }
    }
    return true
}

fun main() {
    println(isCyclic("142857"))
}