package com.example.coroutine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.coroutine.ui.theme.MyApplicationTheme
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
        testCoroutine("test");
    }

    private suspend fun testSuspendCoroutine(jsonBody: String) {
        withContext(Dispatchers.Default){

        }
    }
    private fun testCoroutine(jsonBody: String) {
        fun fetchDoc(i: Int) {

        }
        CoroutineScope(Dispatchers.Default).launch {
            val deferredOne = async { fetchDoc(1) }
            val deferredTwo = async { fetchDoc(2) }
            deferredOne.await()
            deferredTwo.await()
        }
    }


    class ExampleClass {
        private val scope = CoroutineScope(Job() + Dispatchers.Main)

        fun exampleMethod() {
            val job =CoroutineScope(Dispatchers.Default).launch {  }
            // Starts a new coroutine on Dispatchers.Main as it's the scope's default
            val job1 = scope.launch {
                // New coroutine with CoroutineName = "coroutine" (default)
            }

            // Starts a new coroutine on Dispatchers.Default
            val job2 = scope.launch(Dispatchers.Default + CoroutineName("BackgroundCoroutine")) {
                // New coroutine with CoroutineName = "BackgroundCoroutine" (overridden)
            }
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}