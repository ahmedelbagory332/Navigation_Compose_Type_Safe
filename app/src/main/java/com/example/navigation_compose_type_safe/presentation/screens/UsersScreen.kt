package com.example.navigation_compose_type_safe.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.navigation_compose_type_safe.data.FakeData.Users
import com.example.navigation_compose_type_safe.domain.User
import com.example.navigation_compose_type_safe.domain.UserType

@Composable
fun UsersScreen(onClick: (User, UserType) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Click on user to go to details with custom Args")
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(Users.keys.toList()) { user ->
                Text(
                    text = "id : ${user.id} name : ${user.name}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onClick(user, Users[user]!!)
                        }
                        .padding(16.dp)
                )
            }
        }
    }
}