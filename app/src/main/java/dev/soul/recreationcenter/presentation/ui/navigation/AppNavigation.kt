package dev.soul.recreationcenter.presentation.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.soul.recreationcenter.R
import dev.soul.recreationcenter.presentation.ui.base.SharedViewModel
import dev.soul.recreationcenter.presentation.ui.feature.blogInfo.composable.BlogInfoScreen
import dev.soul.recreationcenter.presentation.ui.feature.home.composable.HomeScreen
import dev.soul.recreationcenter.presentation.ui.theme.CustomThemeManager

@Composable
fun AppNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
) {

    NavHost(
        modifier = Modifier.background(CustomThemeManager.colors.screenBackground),
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        composable(
            route = Screens.Home.route
        ) {
            HomeScreen(sharedViewModel = sharedViewModel, navController = navController)
        }

        composable(
            route = Screens.Map.route,
        ) {
        }
        composable(
            route = Screens.Booking.route,
        ) {
        }
        composable(
            route = Screens.Chats.route,
        ) {
        }
        composable(
            route = Screens.Profile.route,
        ) {
        }

        composable(
            route = Screens.Blog.route,
            arguments = listOf(navArgument(BLOG_ARGUMENT_KEY) { type = NavType.IntType })
        ) {

//            val blogInfoViewModel: BlogInfoViewModel = hiltViewModel()
//            blogInfoViewModel.getBlogInfo(it.arguments!!.getInt(BLOG_ARGUMENT_KEY))
            BlogInfoScreen(
                blogId = it.arguments!!.getInt(BLOG_ARGUMENT_KEY),
                navController = navController
            )
        }
    }
}

sealed class Screens(val route: String, val title: String, val icon: Int) {
    object Home : Screens(
        route = "home",
        title = "Главная",
        icon = R.drawable.ic_home
    )

    object Map : Screens(
        route = "map",
        title = "Карта",
        icon = R.drawable.ic_map
    )

    object Booking : Screens(
        route = "booking",
        title = "Бронь",
        icon = R.drawable.ic_booking
    )

    object Chats : Screens(
        route = "chats",
        title = "Чаты",
        icon = R.drawable.ic_chat
    )

    object Profile : Screens(
        route = "profile",
        title = "Профиль",
        icon = R.drawable.ic_profile
    )

    object Blog : Screens(
        route = "blog/{$BLOG_ARGUMENT_KEY}",
        title = "Блог",
        icon = R.drawable.ic_round_back_button
    ) {
        fun passId(blogId: Int) = "blog/$blogId"
    }
}

const val BLOG_ARGUMENT_KEY = "blogId"

//object Navigation {
//
//    object Args {
//        const val BLOG_ID = "user_id"
//    }
//
//    object Routes {
//        const val MAIN = "users"
//        const val BLOGINFO = "$MAIN/{$BLOG_ID}"
//    }
//
//}
//
//fun NavController.navigateToRepos(userId: String) {
//    navigate(route = "${Navigation.Routes.MAIN}/$userId")
//}
