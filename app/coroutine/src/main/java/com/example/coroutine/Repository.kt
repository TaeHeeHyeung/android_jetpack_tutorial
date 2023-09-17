package com.example.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicBoolean


// [START coroutine_test_repo_dispatcher_injection]
// Example class demonstrating dispatcher use cases
class Repository(private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {
    private val scope = CoroutineScope(ioDispatcher)
    val initialized = AtomicBoolean(false)

    // A function that starts a new coroutine on the IO dispatcher
    fun initialize() {
        scope.launch {
            initialized.set(true)
        }
    }

    // A suspending function that switches to the IO dispatcher
    suspend fun fetchData(): String = withContext(ioDispatcher) {
        require(initialized.get()) { "Repository should be initialized first" }
        delay(500L)
        "Hello world"
    }
}
// [END coroutine_test_repo_dispatcher_injection]

// [START coroutine_test_repo_dispatcher_injection_better]
class BetterRepository(private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {
    private val scope = CoroutineScope(ioDispatcher)
    // [START_EXCLUDE silent]
    val initialized = AtomicBoolean(false)
    // [END_EXCLUDE]

    fun initialize() = scope.async {
        // [START_EXCLUDE]
        initialized.set(true)
        // [END_EXCLUDE]
    }
    // [START_EXCLUDE silent]
    suspend fun fetchData(): String = withContext(ioDispatcher) {
        require(initialized.get()) { "Repository should be initialized first" }
        delay(500L)
        "Hello world"
    }
    // [END_EXCLUDE]
}