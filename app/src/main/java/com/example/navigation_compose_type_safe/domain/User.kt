package com.example.navigation_compose_type_safe.domain

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val name: String,
)

enum class UserType {
    Customer,
    Admin,
    Guest
}