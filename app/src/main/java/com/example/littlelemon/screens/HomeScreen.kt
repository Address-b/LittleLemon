package com.example.littlelemon.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemon.R
import com.example.littlelemon.components.MenuItemComposable
import com.example.littlelemon.model.MenuItem
import com.example.littlelemon.ui.theme.LittleLemonColor
import com.example.littlelemon.ui.theme.LittleLemonSecondColor
import com.example.littlelemon.util.Profile

@Composable
fun HomeScreen(myList: List<MenuItem>, navController: NavHostController) {

    var searchQuery by remember { mutableStateOf("") }

    var category by remember { mutableStateOf("") }
    val categories = listOf("All", "Starts", "Mains", "Deserts", "Drinks" )

    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(false) {
        visible = true
    }

    LazyColumn {
        item{
            Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, end = 8.dp)
                ) {

                    // --------------------- The Header --------------------
                    Image(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = "logo",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .width(200.dp),
                        contentScale = ContentScale.Crop
                    )

                    Image(
                        painter = painterResource(R.drawable.profile_image),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(60.dp)
                            .align(Alignment.CenterEnd)
                            .clickable { navController.navigate(Profile.route) }
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .background(LittleLemonColor)
                        .padding(all = 10.dp)
                ) {
                    Text(
                        text = "Little Lemon",
                        fontSize = 36.sp,
                        color = LittleLemonSecondColor
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(
                                text = "Chicago",
                                fontSize = 26.sp,
                                color = Color.White,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            // Description
                            // While the list below may smell to you of sarcasm, it’s meant to be a fresh look at communication gone bad. It’s directional and subjective, honest and yet disposable. There are a few items in this list that could actually get you in trouble.

                            AnimatedVisibility(
                                visible = visible,
                                enter =
                                    slideIn(tween(800, easing = LinearOutSlowInEasing)) { fullSize ->
                                        IntOffset(-fullSize.width / 4, 0)
                                    },
                                exit =
                                    slideOut(tween(100, easing = FastOutSlowInEasing)) {
                                        IntOffset(-180, 50)
                                    },

                                ) {
                                Text(
                                    modifier = Modifier.padding(end = 8.dp),
                                    style = MaterialTheme.typography.displayMedium,
                                    textAlign = TextAlign.Start,
                                    text = "While the list below may smell to you of sarcasm, it’s meant to be a fresh look at commun gone bad. It’s directional and subjective"
                                )
                            }
                        }
                        AnimatedVisibility(
                            visible = visible,
                            enter =
                                slideIn(tween(600, easing = LinearOutSlowInEasing)) { fullSize ->
                                    IntOffset(fullSize.width / 4, 0)
                                },
                            exit =
                                slideOut(tween(100, easing = FastOutSlowInEasing)) {
                                    IntOffset(-180, 50)
                                },

                        ) {
                            Image(
                                painter = painterResource(R.drawable.home_image),
                                modifier = Modifier
                                    .size(150.dp)
                                    .clip(RoundedCornerShape(16.dp)),
                                contentScale = ContentScale.Crop,
                                contentDescription = ""
                            )
                        }

                    }

                    // ------------------------- Search --------------------------------

                    AnimatedVisibility(
                        visible = visible,
                        enter =
                            slideIn(tween(800, easing = LinearOutSlowInEasing)) { fullSize ->
                                IntOffset(-fullSize.width / 4, 0)
                            },
                        exit =
                            slideOut(tween(100, easing = FastOutSlowInEasing)) {
                                IntOffset(-180, 50)
                            },

                        ) {

                        TextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
                            placeholder = { Text(text = "Search our delicious meals") },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp, vertical = 12.dp),
                            shape = RoundedCornerShape(10.dp), colors = TextFieldDefaults.colors()

                        )
                    }



                    if (searchQuery.isNotEmpty()) {
                        myList.filter { it.title.contains(searchQuery, ignoreCase = true) }
                    }


                }


                // ------------------------- Items Categories --------------------------------

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "ORDER FOR DELIVERY!",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 12.dp, bottom = 8.dp)
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp)
                ) {
                    items(categories) {
                        Button(
                            onClick = { category = it },
                            colors = ButtonDefaults.buttonColors(containerColor = if (category == it) LittleLemonColor else Color.LightGray)
                        ) {
                            Text(it, color = if (category == it) Color.White else LittleLemonColor)
                        }
                    }

                }

                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 16.dp),
                    thickness = 0.5.dp
                )

                if (category.isNotBlank()) {
                    myList.filter { it.category == category }
                }

            }
        }
        val filteredList = myList
            .filter { it.title.contains(searchQuery, ignoreCase = true) }
            .filter {
                category.isBlank() || category == "All" || it.category.equals(
                    category,
                    ignoreCase = true
                )
            }
        items(filteredList){
            AnimatedVisibility(
                visible = visible,
                enter =
                    slideIn(tween(800, easing = LinearOutSlowInEasing)) { fullSize ->
                        IntOffset(-fullSize.width / 4, 0)
                    },
                exit =
                    slideOut(tween(100, easing = FastOutSlowInEasing)) {
                        IntOffset(-180, 50)
                    },

                ) {
                MenuItemComposable(it)
            }

        }
    }


}
