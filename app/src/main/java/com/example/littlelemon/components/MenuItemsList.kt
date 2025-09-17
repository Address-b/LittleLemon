package com.example.littlelemon.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import com.example.littlelemon.R
import com.example.littlelemon.model.MenuItem
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun MenuItemComposable(menuItem: MenuItem) {
    val context = LocalContext.current
    Column(modifier =Modifier.background(Color.White)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 3.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = menuItem.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )

                Text(
                    modifier = Modifier.padding(bottom = 5.dp),
                    text = menuItem.description,
                    color = Color.Gray,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    maxLines = 3
                )
                Text(
                    text = "$ ${menuItem.price}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

            }
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(menuItem.image)
                    .placeholder(R.drawable.place_holder)
                    .crossfade(true)
                    .error(R.drawable.place_holder)
                    .build(),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.FillBounds
            )


        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp),
            thickness = 0.5.dp
        )
    }

}

@Preview
@Composable
fun MenuPreview() {
    LittleLemonTheme {
        MenuItemComposable(
            MenuItem(
                category = "Startes",
                description = "This is the description of the product ",
                id = 1,
                image = "",
                price = "$22",
                title = "Greek salad"
            )
        )
    }
}