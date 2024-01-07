import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

suspend fun simulateAsyncTask(id: Int): String {
    val d :Long = (3000*id).toLong()
    delay( d) // Simulating some async task
    return "Result from task $id"
}

fun main() = runBlocking {
    val tasks = List(10) { id ->
        async {
            simulateAsyncTask(id)
        }
    }

    tasks.forEach { deferred ->
        val result = deferred.await()
        println(result)
    }
}
