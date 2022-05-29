class Game {
    var board: Board = Board()
    var players: MutableList<Player> = mutableListOf()
    var currentPlayer: Player

    init {
        players.add(getPlayerObject(typeInput(1), symbolInput()))
        players.add(getPlayerObject(typeInput(2), if (players[0].symbol == "X") "O" else "X"))
        currentPlayer = players.random()

        board.printBoardWithIndexes()
        processTurn()
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

    fun getPlayerObject(type: String, symbol: String): Player {
        return when (type) {
            "H" -> HumanPlayer(symbol, this)
            "C" -> AiPlayer(symbol, this)
            "R" -> RandomPlayer(symbol, this)
            else -> throw IllegalArgumentException("Invalid type")
        }
    }

    fun isFinished(): Boolean {
        if (board.getWinner() != "-") return true
        return false
    }

    fun processTurn() {
        board.grid[currentPlayer.move()] = currentPlayer.symbol
        board.printBoard()

        if (isFinished()) {
            if (board.getWinner() == "") println("It's a tie!")
            else println("${currentPlayer.symbol} wins!")
        } else {
            currentPlayer = if (players[0] == currentPlayer) players[1] else players[0]
            processTurn()
        }
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

    fun getWinner(): String {
        // Horizontal
        for (i in 0..2) {
            if (grid[i * 3] == grid[i * 3 + 1] &&
                            grid[i * 3] == grid[i * 3 + 2] &&
                            grid[i * 3] != "-"
            )
                    return grid[i * 3]
        }

        // Vertical
        for (i in 0..2) {
            if (grid[i] == grid[i + 3] && grid[i] == grid[i + 6] && grid[i] != "-") return grid[i]
        }

        // Diagonal
        if (grid[0] == grid[4] && grid[0] == grid[8] && grid[4] != "-") return grid[0]
        if (grid[2] == grid[4] && grid[2] == grid[6] && grid[4] != "-") return grid[2]

        for (i in 0..8) if (grid[i] == "-") return "-"

        return ""
    }
}

abstract class Player(symbol: String, game: Game) {
    var symbol: String
    var game: Game

    init {
        this.symbol = symbol
        this.game = game
    }

    abstract fun move(): Int
}

class HumanPlayer(symbol: String, game: Game) : Player(symbol, game) {
    override fun move(): Int {
        var move: String
        do {
            println("${this.symbol}'s move: (1) to (9)")
            move = readLine()!!.uppercase()
        } while (move.length != 1 ||
                move.toInt() < 1 ||
                move.toInt() > 9 ||
                game.board.grid[move.toInt() - 1] != "-")
        return move.toInt() - 1
    }
}

class RandomPlayer(symbol: String, game: Game) : Player(symbol, game) {
    override fun move(): Int {
        println("${this.symbol}'s move: (1) to (9)")
        var availableIndexes: MutableList<Int> = mutableListOf()
        for (i in 0..8) if (game.board.grid[i] == "-") availableIndexes.add(i)
        return availableIndexes.random()
    }
}

class AiPlayer(symbol: String, game: Game) : Player(symbol, game) {
    override fun move(): Int {
        println("${this.symbol}'s move: (1) to (9)")

        var bestEval: Int = if (this.symbol == "X") Int.MIN_VALUE else Int.MAX_VALUE
        var index: Int = -1
        for (i in 0..8) {
            if (game.board.grid[i] == "-") {
                game.board.grid[i] = this.symbol
                var eval: Int = minimax(game.board, 0, if (this.symbol == "X") false else true)
                game.board.grid[i] = "-"
                if (this.symbol == "X") {
                    if (eval > bestEval) {
                        bestEval = eval
                        index = i
                    }
                } else {
                    if (eval < bestEval) {
                        bestEval = eval
                        index = i
                    }
                }
            }
        }
        return index
    }

    fun minimax(node: Board, depth: Int, maximizing: Boolean): Int {
        if (node.getWinner() != "-") {
            var eval: Int = 0
            if (node.getWinner() == "X") eval = 1 else if (node.getWinner() == "O") eval = -1
            return eval
        }

        if (maximizing) {
            var maxEval: Int = -10
            for (i in 0..8) {
                if (node.grid[i] == "-") {
                    node.grid[i] = "X"
                    var eval: Int = minimax(node, depth + 1, false)
                    node.grid[i] = "-"
                    maxEval = if (eval > maxEval) eval else maxEval
                }
            }
            return maxEval
        } else {
            var minEval: Int = 10
            for (i in 0..8) {
                if (node.grid[i] == "-") {
                    node.grid[i] = "O"
                    var eval: Int = minimax(node, depth + 1, true)
                    node.grid[i] = "-"
                    minEval = if (eval < minEval) eval else minEval
                }
            }
            return minEval
        }
    }
}

fun main() {
    Game()
}
