package com.example.littlelemon

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(myList: List<MenuItem>) {
    Column(modifier = Modifier.fillMaxSize()){

        /*

        Here implement the image
         */

        // search

        var searchQuery by remember { mutableStateOf("") }
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {searchQuery = it},
            modifier = Modifier.fillMaxWidth().padding(horizontal = 14.dp)
        )
        if (!searchQuery.isBlank()){
            myList.filter { it.title.contains(searchQuery,ignoreCase = true) }
        }

        /*

        implement the categories
         */


        /*

        Display the menu list
         */


    }
}