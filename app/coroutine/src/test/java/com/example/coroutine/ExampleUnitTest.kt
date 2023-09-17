package com.example.coroutine

import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun standardTest()= runTest{
        val userRepo = UserRepository()
        launch { userRepo.register("Alice") }
        launch { userRepo.register("Bob") }

//         assertEquals(listOf("Alice", "Bob"), userRepo.getAllUsers()) //error

        advanceUntilIdle() // Yields to perform the registrations
        assertEquals(listOf("Alice", "Bob"), userRepo.getAllUsers())
    }
}