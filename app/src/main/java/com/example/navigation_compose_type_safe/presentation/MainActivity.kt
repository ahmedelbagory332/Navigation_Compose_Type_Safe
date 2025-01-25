package com.example.navigation_compose_type_safe.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.navigation_compose_type_safe.data.FakeData
import com.example.navigation_compose_type_safe.domain.CustomNavType
import com.example.navigation_compose_type_safe.domain.Destinations
import com.example.navigation_compose_type_safe.domain.User
import com.example.navigation_compose_type_safe.domain.UserType
import com.example.navigation_compose_type_safe.presentation.screens.DetailsScreen
import com.example.navigation_compose_type_safe.presentation.screens.MainScreen
import com.example.navigation_compose_type_safe.presentation.screens.UserDetailsScreen
import com.example.navigation_compose_type_safe.presentation.screens.UsersScreen
import com.example.navigation_compose_type_safe.presentation.theme.Navigation_Compose_Type_SafeTheme
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Navigation_Compose_Type_SafeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(innerPadding)
                }
            }
        }
    }
}

@Composable
fun App(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.MainScreen,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable<Destinations.MainScreen> {
            MainScreen(
                onClick = {
                    navController.navigate(
                        Destinations.DetailsScreen(
                            name = FakeData.name,
                            age = FakeData.age
                        )
                    )
                }
            )
        }
        // this screen with simple args
        composable<Destinations.DetailsScreen> {
            val args = it.toRoute<Destinations.DetailsScreen>()
            DetailsScreen(
                args = args,
                onClick = {
                    navController.navigate(Destinations.UsersScreen)
                }
            )
        }
        composable<Destinations.UsersScreen> {
            UsersScreen(
                onClick = { user, type ->
                    navController.navigate(
                        Destinations.UserDetailsScreen(
                            user = user,
                            userType = type
                        )
                    )
                }
            )
        }
        // this screen with custom args
        composable<Destinations.UserDetailsScreen>(
            typeMap = mapOf(
                typeOf<User>() to CustomNavType.UserType,
                typeOf<UserType>() to NavType.EnumType(UserType::class.java)
            )
        ) {
            val arguments = it.toRoute<Destinations.UserDetailsScreen>()
            UserDetailsScreen(user = arguments.user, userType = arguments.userType)
        }
    }
}



