package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json()
        }
    }

    private val menuItemsLiveData = MutableLiveData<List<MenuItem>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        lifecycleScope.launch {
            val items = getMenu()

                menuItemsLiveData.value = items
        }

        enableEdgeToEdge()
        setContent {
            LittleLemonTheme {
                val myList by menuItemsLiveData.observeAsState(emptyList())
                MyNav(myList)
            }
        }
    }

    private suspend fun getMenu(): List<MenuItem> {
        val response: MenuNetworkData = client
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/littleLemonMenu.json")
            .body()

        return response.menu
    }
}

@Composable
fun MyNav(myList: List<MenuItem>){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home.route){
        composable(OnBoarding.route){
            OnBoardingScreen()
        }
        composable(Home.route){
            HomeScreen(myList)
        }
        composable(Profile.route){
            ProfileScreen(navController)
        }
    }
}