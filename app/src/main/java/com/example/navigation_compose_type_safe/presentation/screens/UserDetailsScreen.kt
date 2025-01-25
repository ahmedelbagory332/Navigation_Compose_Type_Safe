package com.example.navigation_compose_type_safe.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.navigation_compose_type_safe.domain.User
import com.example.navigation_compose_type_safe.domain.UserType

@Composable
fun UserDetailsScreen(user: User, userType: UserType) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${user.name} with the ID of ${user.id} is $userType"
        )
    }
}