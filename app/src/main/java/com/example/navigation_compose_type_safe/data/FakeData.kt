package com.example.navigation_compose_type_safe.data

import com.example.navigation_compose_type_safe.domain.User
import com.example.navigation_compose_type_safe.domain.UserType

object FakeData {
    const val name: String = "Ahmed"
    const val age: Int = 26
    val Users = mapOf(
        User(1, "Ahmed") to UserType.Admin,
        User(2, "Mohammed") to UserType.Guest,
        User(3, "Youssef") to UserType.Customer,
        User(4, "Nader") to UserType.Admin,
        User(5, "Omar") to UserType.Customer,
        User(6, "Yasser") to UserType.Guest
    )
}
