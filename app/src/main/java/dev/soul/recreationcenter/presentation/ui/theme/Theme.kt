package dev.soul.recreationcenter.presentation.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf


private val CustomDarkColor = CustomColor(
    screenBackground = screenBackgroundColorDark,
    textColor = textColorColorDark,
    bottomNavBackground = bottomNavBackgroundColorDark,
    bottomNavItem = bottomNavItemColorDark,
    dividerColor = dividerNightColor

)

private val CustomLightColor = CustomColor(
    screenBackground = screenBackgroundColorLight,
    textColor = textColorColorLight,
    bottomNavBackground = bottomNavBackgroundColorLight,
    bottomNavItem = bottomNavItemColorLight,
    dividerColor = dividerLightColor
)

private val localProvider = staticCompositionLocalOf {
    CustomLightColor
    CustomDarkColor
}

@Composable
private fun CustomLocalProvider(colors: CustomColor, content: @Composable () -> Unit) {
    val colorPalate = remember { colors.copy() }

    colorPalate.updateColor(colors)

    CompositionLocalProvider(localProvider provides colorPalate, content = content)

}

private val CustomTheme.colors: Pair<Colors, CustomColor>
    get() = when (this) {
        CustomTheme.DARK -> darkColors() to CustomDarkColor
        CustomTheme.LIGHT -> lightColors() to CustomLightColor
    }


object CustomThemeManager {
    val colors: CustomColor
        @Composable
        get() = localProvider.current

    var customTheme by mutableStateOf(CustomTheme.LIGHT)

    fun isSystemInDarkTheme(): Boolean {
        return customTheme == CustomTheme.DARK
    }

}

@Composable
fun RecreationCenterTheme(
    custom: CustomTheme = CustomThemeManager.customTheme,
    content: @Composable () -> Unit
) {
    val (colorScheme, colors) = custom.colors

    CustomLocalProvider(colors = colors) {
        MaterialTheme(
            colors = colorScheme,
            typography = Typography,
            content = content
        )
    }
}