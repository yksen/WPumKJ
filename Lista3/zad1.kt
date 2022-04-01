class Hangman {

    public var isEnabled: Boolean = true
    public var lives: Int = 7
    private var words: List<String> = listOf()
    private var word: String = ""
    private var guess: String = ""
    private var guessedLetters: List<String> = listOf()

    private var hangmanState: List<String> =
            listOf(
"""
    +---+
    O   |
   /|\  |
   / \  |
       ===""",
"""
    +---+
    O   |
   /|\  |
   /    |
       ===""",
"""
    +---+
    O   |
   /|\  |
        |
       ===""",
"""
    +---+
    O   |
   /|   |
        |
       ===""",
"""
    +---+
    O   |
    |   |
        |
       ===""",
"""
    +---+
    O   |
        |
        |
       ===""",
"""
    +---+
        |
        |
        |
       ==="""
            )

    fun init() {
        println("Ładowanie...")
        this.words = importWords("slowa.txt")
        this.words = this.words.filter { !it.any { "ąćęłńóśźż".contains(it) } }
        println("Załadowano ${this.words.size} słów\n")
    }

    fun importWords(fileName: String): List<String> =
            java.io.File(fileName).useLines { it.toList() }

    fun start() {
        this.lives = 7
        this.guessedLetters = listOf()
        this.word = this.words.random()
        this.guess = this.word

        println("Gra rozpoczęta!")
        println("Wylosowano słowo o długości ${this.word.length} znaków")

        while (this.lives > 0) {
            this.guess =
                    this.word
                            .map { if (it.toString() in guessedLetters) it else "_" }
                            .joinToString("")
            if (this.word == this.guess) {
                println("\nWygrałeś!\nOdpowiedź: ${this.word}")
                break
            }
            println(hangmanState[this.lives - 1])
            println("\nHasło: ${guess}")
            println("Życia: ${this.lives}")
            println("Wykorzystane litery: ${this.guessedLetters}")

            var letter: String
            do {
                println("Podaj literę:")
                letter = readLine()!!.lowercase()
            } while (letter !in "a".."z" || letter.length != 1 || letter in guessedLetters)

            this.guessedLetters += letter
            this.guessedLetters = this.guessedLetters.sorted()

            if (!this.word.contains(letter)) this.lives--
            if (this.lives == 0) println("\nPrzegrałeś!\nOdpowiedź: ${this.word}")
        }
    }

    fun stop() {
        this.isEnabled = false
    }
}

fun main() {
    val game = Hangman()
    game.init()
    while (game.isEnabled) {
        game.start()
        println("Czy chcesz zagrać jeszcze raz? (T/N)")
        if (readLine()!!.lowercase() == "n") game.stop()
    }
}
