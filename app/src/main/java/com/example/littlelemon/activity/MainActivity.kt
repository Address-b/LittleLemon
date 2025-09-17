package com.example.littlelemon.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.model.MenuItem
import com.example.littlelemon.model.MenuResponse
import com.example.littlelemon.screens.HomeScreen
import com.example.littlelemon.screens.OnBoardingScreen
import com.example.littlelemon.screens.ProfileScreen
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.util.Home
import com.example.littlelemon.util.OnBoarding
import com.example.littlelemon.util.Profile
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
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
        val response: MenuResponse = client
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body()

        return response.menu
    }
}

@Composable
fun MyNav(myList: List<MenuItem>){
    val context = LocalContext.current
    val navController = rememberNavController()
    val prefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

    val isRegistered = prefs.getBoolean("isRegistered",false)

    val startDestination = if (isRegistered) Home.route else OnBoarding.route

    NavHost(navController = navController, startDestination = startDestination){
        composable(OnBoarding.route){
            OnBoardingScreen(navController)
        }
        composable(Home.route){
            HomeScreen(myList, navController)
        }
        composable(Profile.route){
            ProfileScreen(navController)
        }
    }
}