package com.example.navigation_compose_type_safe.domain

import kotlinx.serialization.Serializable

sealed class Destinations {
    @Serializable
    data object MainScreen : Destinations()

    @Serializable
    data class DetailsScreen(
        val name: String,
        val age: Int
    ) : Destinations()

    @Serializable
    data object UsersScreen : Destinations()


    @Serializable
    data class UserDetailsScreen(val user: User, val userType: UserType) : Destinations()
}