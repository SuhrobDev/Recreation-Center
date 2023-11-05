package dev.soul.recreationcenter.presentation.ui.feature.main.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.soul.recreationcenter.presentation.ui.base.SharedViewModel
import dev.soul.recreationcenter.presentation.ui.navigation.AppNavigation
import dev.soul.recreationcenter.presentation.ui.navigation.BottomNav
import dev.soul.recreationcenter.presentation.ui.navigation.Screens
import dev.soul.recreationcenter.presentation.ui.theme.CustomThemeManager
import dev.soul.recreationcenter.presentation.ui.theme.toolBarTextSize
import dev.soul.recreationcenter.presentation.utils.customFont

@Composable
fun MainScreen(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
) {

    var title by remember { mutableStateOf("") }
    var showBottomBar by rememberSaveable { mutableStateOf(true) }
    var showTopBar by rememberSaveable { mutableStateOf(true) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    showBottomBar = when (navBackStackEntry?.destination?.route) {
        Screens.Blog.route -> false
        else -> true
    }
    showTopBar = when (navBackStackEntry?.destination?.route) {
        Screens.Blog.route -> false
        else -> true
    }

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            title = when (backStackEntry.destination.route) {
                Screens.Home.route -> Screens.Home.title
                Screens.Blog.route -> Screens.Blog.title
                Screens.Booking.route -> Screens.Booking.title
                Screens.Chats.route -> Screens.Chats.title
                Screens.Map.route -> Screens.Map.title
                Screens.Profile.route -> Screens.Profile.title
                else -> ""
            }
        }
    }

    Scaffold(
        topBar = { if (showTopBar) MainToolBar(title = sharedViewModel.title) },
        bottomBar = { if (showBottomBar) BottomNav(navController = navController) },
        content = {
            Box(modifier = Modifier.padding(it)) {
                AppNavigation(navController = navController, sharedViewModel = sharedViewModel)
            }
        },
        backgroundColor = CustomThemeManager.colors.screenBackground,
    )
}

@Composable
fun MainToolBar(
    title: String,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = toolBarTextSize,
                fontWeight = FontWeight.Normal,
                fontFamily = customFont,
                color = CustomThemeManager.colors.textColor
            )
        },
        backgroundColor = CustomThemeManager.colors.screenBackground,
        contentColor = CustomThemeManager.colors.textColor,
    )
}