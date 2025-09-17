package com.example.littlelemon.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.core.content.edit
import androidx.navigation.NavHostController
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.LittleLemonColor
import com.example.littlelemon.util.Home

@Composable
fun OnBoardingScreen(navController: NavHostController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val context = LocalContext.current


    val prefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

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
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(LittleLemonColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Let's get to know you!", fontSize = 34.sp,
                color = Color.White, modifier = Modifier.padding(vertical = 50.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

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
                // 1- save user information  2- navigate to Home screen

                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
                    Toast.makeText(context, "All fields are required!", Toast.LENGTH_SHORT).show()
                } else {
                    prefs.edit {
                        putString("firstName", firstName)
                        putString("lastName", lastName)
                        putString("email", email)
                        putBoolean("isRegistered", true)
                    }
                    navController.navigate(Home.route)
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFE8A300))
        ) {
            Text("Register", color = Color.Black)
        }

    }


}