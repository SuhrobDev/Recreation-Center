package dev.soul.recreationcenter.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import dev.soul.recreationcenter.presentation.ui.base.SharedViewModel
import dev.soul.recreationcenter.presentation.ui.feature.main.composable.MainScreen
import dev.soul.recreationcenter.presentation.ui.theme.CustomTheme
import dev.soul.recreationcenter.presentation.ui.theme.CustomThemeManager
import dev.soul.recreationcenter.presentation.ui.theme.RecreationCenterTheme
import dev.soul.recreationcenter.presentation.ui.theme.screenBackgroundColorDark
import dev.soul.recreationcenter.presentation.ui.theme.screenBackgroundColorLight

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val sharedViewModel: SharedViewModel = hiltViewModel()
            val navController = rememberNavController()
            val darkMode = isSystemInDarkTheme()
            CustomThemeManager.customTheme =
                if (darkMode) {
                    CustomTheme.DARK
                } else {
                    CustomTheme.LIGHT
                }
            StatusBarColor()
            RecreationCenterTheme {
                MainScreen(
                    sharedViewModel = sharedViewModel,
                    navController = navController
                )

            }
        }
    }

    @Composable
    fun StatusBarColor() {
        val systemUiController = rememberSystemUiController()
        if (CustomThemeManager.isSystemInDarkTheme()) {
            systemUiController.setSystemBarsColor(
                color = screenBackgroundColorDark
            )
        } else {
            systemUiController.setSystemBarsColor(
                color = screenBackgroundColorLight
            )
        }
    }
}
