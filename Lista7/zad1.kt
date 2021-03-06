import java.math.BigInteger
import java.util.concurrent.*

fun factorial(n: Long): BigInteger {
    var calculatedProduct = BigInteger.ONE
    val NUM_THREADS = 10

    val executorService = Executors.newFixedThreadPool(NUM_THREADS)
    val multiplicationTasks: MutableList<Future<BigInteger>> = ArrayList()
    val taskNum: Long = Math.ceil(n / NUM_THREADS.toDouble()).toLong()

    for (i in 0 until NUM_THREADS) {
        val fromInInnerRange = taskNum * i + 1
        val toInInnerRange = if (taskNum * (i + 1) > n) n else taskNum * (i + 1)
        val multiplicationTask: Callable<BigInteger> = Product(fromInInnerRange, toInInnerRange)
        val futureProduct = executorService.submit(multiplicationTask)
        multiplicationTasks.add(futureProduct)
    }

    for (partialProduct in multiplicationTasks) {
        try {
            calculatedProduct = calculatedProduct.multiply(partialProduct.get())
        } catch (exception: CancellationException) {
            exception.printStackTrace()
        } catch (exception: ExecutionException) {
            exception.printStackTrace()
        } catch (exception: InterruptedException) {
            exception.printStackTrace()
        }
    }
    executorService.shutdown()
    return calculatedProduct
}

internal class Product(var from: Long, var to: Long) : Callable<BigInteger> {
    var localProduct: BigInteger = BigInteger.ONE
    override fun call(): BigInteger {
        for (i in from..to) localProduct = localProduct.multiply(BigInteger.valueOf(i))
        return localProduct
    }
}

fun main() {
    val n: Long = 100L
    println("$n! = ${factorial(n)}")
}
