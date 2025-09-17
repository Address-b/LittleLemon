package com.example.littlelemon.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemon.R
import com.example.littlelemon.util.OnBoarding
import androidx.core.content.edit

@Composable
fun ProfileScreen(navController: NavHostController) {
    val context = LocalContext.current
    var firstName by remember {
        mutableStateOf(
            getInfo("firstName", context) ?: ""
        )
    }

    var lastName by remember {
        mutableStateOf(
            getInfo("lastName", context) ?: ""
        )
    }

    var email by remember {
        mutableStateOf(
            getInfo("email", context) ?: ""
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .width(200.dp)
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(200.dp))

        Text(
            text = "Personal information",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(28.dp))

        Text("First name", modifier = Modifier.padding(start = 14.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            value = firstName,
            onValueChange = { firstName = it },
            shape = RoundedCornerShape(10.dp)
        )

        Text("Last name", modifier = Modifier.padding(start = 14.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            value = lastName,
            onValueChange = { lastName = it },
            shape = RoundedCornerShape(10.dp)
        )

        Text("Email", modifier = Modifier.padding(start = 14.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 28.dp),
            value = email,
            onValueChange = { email = it },
            shape = RoundedCornerShape(10.dp)
        )

        Button(
            onClick = {

                //First DELETE THE DATA THEN NAVIGATE TO hOME SCREEN
                deleteInfo(context)

                navController.navigate(OnBoarding.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFE8A300))
        ) {
            Text("Logout", color = Color.Black)
        }

    }

}

fun getInfo(name: String, context: Context): String? {
    return context.getSharedPreferences(
        "my_prefs",
        Context.MODE_PRIVATE
    ).getString(name, "")
}

fun deleteInfo(context: Context) {
    return context.getSharedPreferences(
        "my_prefs",
        Context.MODE_PRIVATE
    ).edit { clear() }
}