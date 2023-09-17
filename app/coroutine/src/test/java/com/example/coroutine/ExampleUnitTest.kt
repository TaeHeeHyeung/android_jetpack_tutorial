package com.example.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

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






    class MyViewModelTest {
        class UserRepository {
            var userList: ArrayList<String> = arrayListOf<String>()
            fun register(s: String) {
                userList.add(s)
            }

            fun getAllUsers(): Any? {
                return userList
            }

        }
        @OptIn(ExperimentalCoroutinesApi::class)
        @Test
        fun unconfinedTest1() = runTest(UnconfinedTestDispatcher()) {
            val userRepo = UserRepository()

            launch { userRepo.register("Alice") }
            launch { userRepo.register("Bob") }

            assertEquals(listOf("Alice", "Bob"), userRepo.getAllUsers()) // ✅ Passes
        }
        @OptIn(ExperimentalCoroutinesApi::class)
        @Test
        fun yieldingTest() = runTest(UnconfinedTestDispatcher()) {
            val userRepo = UserRepository()

            launch {
                userRepo.register("Alice")
                delay(10L)
                userRepo.register("Bob")
            }

            //assertEquals(listOf("Alice", "Bob"), userRepo.getAllUsers()) // ❌ Fails
        }
    }
    suspend fun fetchData():String{
        delay(1000L)
        return "Hello"
    }
    @Test
    fun dataShouldBeHelloWorld() = runTest{
        assertEquals(fetchData(), "Hello")
    }

    class RepositoryTest {
        @Test
        fun repoInitWorksAndDataIsHelloWorld() = runTest {
            val dispatcher = StandardTestDispatcher(testScheduler)
            val repository = Repository(dispatcher)

            repository.initialize()
            advanceUntilIdle() // Runs the new coroutine
            assertEquals(true, repository.initialized.get())

            val data = repository.fetchData() // No thread switch, delay is skipped
            assertEquals("Hello world", data)
        }
    }

    @Test
    fun repoInitWorks() = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)
        val repository = BetterRepository(dispatcher)

        repository.initialize().await() // Suspends until the new coroutine is done
        assertEquals(true, repository.initialized.get())
    }

}