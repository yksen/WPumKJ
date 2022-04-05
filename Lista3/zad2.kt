import kotlin.collections.mutableListOf

class RankedVoting(val _candidates: List<String>, val _voters: Int) {
    val candidates: List<String>
    val voters: Int

    init {
        this.candidates = _candidates
        this.voters = _voters
    }

    fun start() {
        count(vote())
    }

    fun vote(): Array<Array<String>> {
        var votes = Array<Array<String>>(voters, { Array<String>(candidates.size, { "" }) })
        for (i in 0 until voters) {
            for (j in 1..candidates.size) {
                var vote: String
                do {
                    print("Rank " + j + ": ")
                    vote = readLine()!!
                } while (vote !in candidates)
                votes[i][j - 1] = vote
            }
            println()
        }
        return votes
    }

    fun count(votes: Array<Array<String>>) {
        // Tally
        val amountOfPairs: Int = factorial(candidates.size) / (2 * factorial(candidates.size - 2))
        var pairs: MutableList<Pair<String, String>> = mutableListOf()
        var pairVotes: Array<Pair<Int, Int>> = Array(amountOfPairs, { Pair(0, 0) })

        for (i in 0 until candidates.size - 1) {
            for (j in i + 1 until candidates.size) {
                pairs.add(Pair(candidates[i], candidates[j]))
            }
        }

        for (i in 0 until voters) {
            for (pair in pairs) {
                if (votes[i].indexOf(pair.first) < votes[i].indexOf(pair.second))
                        pairVotes[pairs.indexOf(pair)] =
                                Pair(
                                        pairVotes[pairs.indexOf(pair)].first + 1,
                                        pairVotes[pairs.indexOf(pair)].second
                                )
                else
                        pairVotes[pairs.indexOf(pair)] =
                                Pair(
                                        pairVotes[pairs.indexOf(pair)].first,
                                        pairVotes[pairs.indexOf(pair)].second + 1
                                )
            }
        }
        println(pairs.toList().toString())
        println(pairVotes.toList().toString())

        // Sort
        println(pairVotes.sortBy { it.first })

        // Lock

    }

    fun factorial(n: Int): Int {
        if (n == 0) {
            return 1
        }
        return n * factorial(n - 1)
    }
}

fun main() {
    val rv = RankedVoting(listOf("A", "B", "C"), 5)
    rv.start()
}
