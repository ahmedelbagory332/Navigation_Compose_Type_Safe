package com.example.navigation_compose_type_safe.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.navigation_compose_type_safe.data.FakeData
import com.example.navigation_compose_type_safe.domain.Destinations
import com.example.navigation_compose_type_safe.presentation.screens.DetailsScreen
import com.example.navigation_compose_type_safe.presentation.screens.MainScreen
import com.example.navigation_compose_type_safe.presentation.screens.UsersScreen
import com.example.navigation_compose_type_safe.presentation.theme.Navigation_Compose_Type_SafeTheme

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
                args= args,
                onClick = {

                }
            )
        }
    }
}



