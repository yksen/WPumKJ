import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import java.util.concurrent.*

fun main() {
    val n: Long = 17L
    println("n! = ${sumOfFactorialInverses(n)}")
}

fun factorial(n: Long): BigDecimal {
    var calculatedProduct = BigDecimal.ONE
    val NUM_THREADS = 10

    val executorService = Executors.newFixedThreadPool(NUM_THREADS)
    val multiplicationTasks: MutableList<Future<BigDecimal>> = ArrayList()
    val taskNum: Long = Math.ceil(n / NUM_THREADS.toDouble()).toLong()

    for (i in 0 until NUM_THREADS) {
        val fromInInnerRange = taskNum * i + 1
        val toInInnerRange = if (taskNum * (i + 1) > n) n else taskNum * (i + 1)
        val multiplicationTask: Callable<BigDecimal> = Product(fromInInnerRange, toInInnerRange)
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

fun sumOfFactorialInverses(n: Long): BigDecimal {
    var sum: BigDecimal = BigDecimal.ZERO
    for (i in 0..n) {
        sum += BigDecimal.ONE.divide(factorial(i), MathContext(100, RoundingMode.HALF_EVEN))
    }
    return sum
}

internal class Product(var from: Long, var to: Long) : Callable<BigDecimal> {
    var localProduct: BigDecimal = BigDecimal.ONE
    override fun call(): BigDecimal {
        for (i in from..to) localProduct = localProduct.multiply(BigDecimal.valueOf(i))
        return localProduct
    }
}