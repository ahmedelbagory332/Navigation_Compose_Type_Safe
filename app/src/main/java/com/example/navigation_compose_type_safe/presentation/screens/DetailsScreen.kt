package com.example.navigation_compose_type_safe.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.navigation_compose_type_safe.domain.Destinations

@Composable
fun DetailsScreen(onClick: () -> Unit, args: Destinations.DetailsScreen) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "User Name: ${args.name}")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "User Age: ${args.age}")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            onClick()
        }) {
            Text(text = "Go to users screen")
        }
    }
}