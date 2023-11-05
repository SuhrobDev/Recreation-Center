package dev.soul.recreationcenter.presentation.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.soul.recreationcenter.presentation.ui.theme.CustomThemeManager
import dev.soul.recreationcenter.presentation.utils.roboto400

@Composable
fun BottomNav(navController: NavHostController) {

    val screens = listOf(
        Screens.Home,
        Screens.Map,
        Screens.Booking,
        Screens.Chats,
        Screens.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(backgroundColor = CustomThemeManager.colors.screenBackground) {
        screens.forEach {
            AddItem(
                screen = it,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: Screens,
    currentDestination: NavDestination?,
    navController: NavHostController
) {

    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route;
    val selected = currentRoute == screen.route

    BottomNavigationItem(
        label = {

            Text(
                text = screen.title,
                style = TextStyle(lineHeight = 16.sp),
                color = if (selected) Color(0xFF4096FB) else Color(0xFFB5B5B5),
                fontFamily = roboto400,
                fontSize = 12.sp,

                )
        },
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = screen.icon),
                contentDescription = "Navigation Icon",
                tint = if (selected) Color(0xFF4096FB) else Color(0xFFB5B5B5),
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            if (navController.currentDestination!!.route == screen.route) return@BottomNavigationItem
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        modifier = Modifier.background(CustomThemeManager.colors.screenBackground)
    )
}