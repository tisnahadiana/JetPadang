package id.tisnahadiana.jetpadang.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Order : Screen("order")
    object About : Screen("about")
    object DetailOrder : Screen("home/{orderId}") {
        fun createRoute(orderId: Long) = "home/$orderId"
    }
}