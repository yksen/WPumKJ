import java.util.concurrent.*

internal class DivisorCount(var from: Int, var to: Int) : Callable<Pair<Int, Int>> {
    override fun call(): Pair<Int, Int> {
        var number: Int = 0
        var highestCount: Int = 0
        for (i in from..to) {
            var count: Int = 1
            for (j in 1..i / 2) if (i % j == 0) ++count
            if (count > highestCount) {
                highestCount = count
                number = i
            }
        }
        return Pair<Int, Int>(number, highestCount)
    }
}

fun main() {
    val NUM_THREADS = 10
    val n: Int = 100000
    var number: Int = 0
    var highestCount: Int = 0

    val executorService = Executors.newFixedThreadPool(NUM_THREADS)
    val countingTasks: MutableList<Future<Pair<Int, Int>>> = ArrayList()
    val taskNum: Int = Math.ceil(n / NUM_THREADS.toDouble()).toInt()

    for (i in 0 until NUM_THREADS) {
        val fromInInnerRange = taskNum * i + 1
        val toInInnerRange = if (taskNum * (i + 1) > n) n else taskNum * (i + 1)
        val countingTask: Callable<Pair<Int, Int>> = DivisorCount(fromInInnerRange, toInInnerRange)
        val futureCount = executorService.submit(countingTask)
        countingTasks.add(futureCount)
    }

    for (count in countingTasks) {
        try {
            if (count.get().second > highestCount) {
                number = count.get().first
                highestCount = count.get().second
            }
        } catch (exception: CancellationException) {
            exception.printStackTrace()
        } catch (exception: ExecutionException) {
            exception.printStackTrace()
        } catch (exception: InterruptedException) {
            exception.printStackTrace()
        }
    }
    executorService.shutdown()

    println("Number = $number\nDivisor count = $highestCount")
}
