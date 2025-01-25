

# Compse Navigation

## A basic guide to compose navigation with latest type safe args
### In this app we use

 - navigation Compose library with 2.8.5 version
 - kotlin serialization

![1_7OC7BTCSyt_KuskGeHuDgQ](https://github.com/user-attachments/assets/83bf8db9-fe91-4261-bff7-de0a4822fbff)

## Define routes
### To use type-safe routes in Compose, you first need to define serializable classes or objects that represent your routes.
```
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
```
## Build your graph
```
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
```
## For Custom Arg routes we tell compose how to serialize and deserialize arg like 
```
object CustomNavType {

    val UserType = object : NavType<User>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): User? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): User {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: User): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: User) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}
```

<table>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/5f0f7bc4-289d-453e-897d-6e7fdd3ea404" width=270 height=480></td>
    <td><img src="https://github.com/user-attachments/assets/6686abf4-6866-4185-8fa4-2b88417849c0" width=270 height=480></td>
    <td><img src="https://github.com/user-attachments/assets/e14c3060-a674-4a26-90d5-bd83408b1b79" width=270 height=480></td>
    <td><img src="https://github.com/user-attachments/assets/7080fd98-0641-4d6d-8bb2-a89fc1a5d577" width=270 height=480></td>
   </tr>
 </table>

