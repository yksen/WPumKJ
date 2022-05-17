class Game {
    var board: Board
    var players: MutableList<Player> = mutableListOf()
    var currentPlayer: Player
    var currentPlayerIndex: Int

    init {
        board = Board()
        players.add(classFromType(typeInput(1), symbolInput()))
        players.add(classFromType(typeInput(2), if (players[0].symbol == "X") "O" else "X"))
        currentPlayerIndex = (0..1).random()
        currentPlayer = players[currentPlayerIndex]

        board.printBoardWithIndexes()
        currentPlayer.move()
    }

    fun typeInput(num: Int): String {
        var type: String
        do {
            println("Choose Player ${num}'s type: (H)uman, (C)omputer, (R)andom")
            type = readLine()!!.uppercase()
        } while (type.length != 1 || !(type == "H" || type == "C" || type == "R"))
        return type
    }

    fun classFromType(type: String, symbol: String): Player {
        return when (type) {
            "H" -> HumanPlayer(symbol)
            "C" -> AiPlayer(symbol)
            "R" -> RandomPlayer(symbol)
            else -> Player(symbol)
        }
    }

    fun symbolInput(): String {
        var symbol: String
        do {
            println("Choose your symbol: (X) or (O)")
            symbol = readLine()!!.uppercase()
        } while (symbol.length != 1 || !(symbol == "X" || symbol == "O"))
        return symbol
    }
}

class Board {
    var grid: MutableList<String> = mutableListOf("-", "-", "-", "-", "-", "-", "-", "-", "-")

    fun printBoardWithIndexes() {
        println("\n1   2   3")
        println("4   5   6")
        println("7   8   9")
    }

    fun printBoard() {
        println()
        for (i in 0 until 3) {
            println(grid[i * 3] + "   " + grid[i * 3 + 1] + "   " + grid[i * 3 + 2])
        }
    }

    fun checkWin(): Int {
        for (i in 0..2) {
            if (grid[i * 3] == grid[i * 3 + 1] && grid[i * 3] == grid[i * 3 + 2]) {}
        }
        return 0
    }
}

open class Player(_symbol: String) {
    var symbol: String

    init {
        this.symbol = _symbol
    }

    open fun move() {}
}

class HumanPlayer(_symbol: String) : Player(_symbol) {
    override fun move() {
        // var move: String
        // do {
        //     println("${this.symbol}'s move: (1) to (9)")
        //     move = readLine()!!.uppercase()
        // } while (move.length != 1 ||
        //         move.toInt() < 1 ||
        //         move.toInt() > 9 ||
        //         board.grid[move.toInt() - 1] != "-")

        // board.grid[move.toInt() - 1] = this.symbol
        // board.printBoard()
        // currentPlayerIndex = (currentPlayerIndex + 1) % 2
        // currentPlayer = players[currentPlayerIndex]

        // if (board.checkWin() == -1) {
        //     move()
        // } else {
        //     println("Game has ended")
        // }
    }
}

class RandomPlayer(_symbol: String) : Player(_symbol) {
    override fun move() {}
}

class AiPlayer(_symbol: String) : Player(_symbol) {
    override fun move() {}
}

fun main() {
    Game()
}
