class Game {
    var board: Board
    var players: MutableList<Player> = mutableListOf()
    var currentPlayer: Player
    var currentPlayerIndex: Int

    init {
        board = Board()
        players.add(Player(typeInput(1), symbolInput()))
        players.add(Player(typeInput(2), if (players[0].symbol == "X") "O" else "X"))
        currentPlayerIndex = (0..1).random()
        currentPlayer = players[currentPlayerIndex]

        board.printBoardWithIndexes()
        move()
    }

    fun typeInput(num: Int): String {
        var type: String
        do {
            println("Choose Player ${num}'s type: (H)uman, (C)omputer, (R)andom")
            type = readLine()!!.uppercase()
        } while (type.length != 1 || !(type == "H" || type == "C" || type == "R"))
        return type
    }

    fun symbolInput(): String {
        var symbol: String
        do {
            println("Choose your symbol: (X) or (O)")
            symbol = readLine()!!.uppercase()
        } while (symbol.length != 1 || !(symbol == "X" || symbol == "O"))
        return symbol
    }

    fun move() {
        var move: String
        do {
            println("${currentPlayer.symbol}'s move: (1) to (9)")
            move = readLine()!!.uppercase()
        } while (move.length != 1 ||
                move.toInt() < 1 ||
                move.toInt() > 9 ||
                board.grid[move.toInt() - 1] != "-")

        board.grid[move.toInt() - 1] = currentPlayer.symbol
        board.printBoard()
        currentPlayerIndex = (currentPlayerIndex + 1) % 2
        currentPlayer = players[currentPlayerIndex]

        if (board.checkWin() == -1) {
            move()
        }
        else {
            println("Game has ended")
        }
    }
}

class Board {
    var grid: MutableList<String> = mutableListOf("-", "-", "-", "-", "-", "-", "-", "-", "-")

    fun printBoardWithIndexes() {
        println("1   2   3")
        println("4   5   6")
        println("7   8   9")
    }

    fun printBoard() {
        for (i in 0..8) {
            print(grid[i])
            if (i % 3 == 2) {
                println()
            }
        }
    }

    fun checkWin(): Int {
        for (i in 0..2) {
            if (grid[i * 3] == grid[i * 3 + 1] && grid[i * 3] == grid[i * 3 + 2]) {
            
            }
        }
    }
}

class Player(_type: String, _symbol: String) {
    var type: String
    var symbol: String

    init {
        if (_type == "R") {
            this.type = listOf("H", "C").random()
        } else {
            this.type = _type
        }
        this.symbol = _symbol
    }
}

fun main() {
    val game: Game = Game()
}
