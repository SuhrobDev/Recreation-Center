package dev.soul.recreationcenter.presentation.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

val toolBarBackgroundColor = Color(0xFFFFFFFF)

val dividerNightColor = Color(0xFF333333)
val dividerLightColor = Color(0xFFF5F5F5)

val yellowBackground1 = Color(0xFFFE8701)
val yellowBackground2 = Color(0xFFF83A01)

val blueBackground1 = Color(0xFF4680CA)
val blueBackground2 = Color(0xFF5691C8)

val routeBackground1 = Color(0xFF485462)
val routeBackground2 = Color(0xFF323B46)

val blue = Color(0xFF03A9F4)

val String.color
    get() = Color(android.graphics.Color.parseColor(this))


enum class CustomTheme {
    DARK, LIGHT
}

//colors------------------------------------------------
val screenBackgroundColorLight = Color(0xFFFFFFFF)
val screenBackgroundColorDark = Color(0xFF1A1A1A)

val textColorColorLight = Color(0xFF1A1A1A)
val textColorColorDark = Color(0xFFFAFAFA)

val bottomNavBackgroundColorLight = Color(0xFFFFFFFF)
val bottomNavBackgroundColorDark = Color(0xFFFFFFFF)

val bottomNavItemColorLight = Color(0xFF1A1A1A)
val bottomNavItemColorDark = Color(0xFF1A1A1A)


@Stable
class CustomColor(
    screenBackground: Color,
    textColor: Color,
    bottomNavBackground: Color,
    bottomNavItem: Color,
    dividerColor: Color
) {
    var screenBackground by mutableStateOf(screenBackground)
        private set

    var textColor by mutableStateOf(textColor)
        private set

    var bottomNavBackground by mutableStateOf(bottomNavBackground)
        private set

    var bottomNavItem by mutableStateOf(bottomNavItem)
        private set

    var dividerColor by mutableStateOf(dividerColor)
        private set


    fun updateColor(color: CustomColor) {
        screenBackground = color.screenBackground
        textColor = color.textColor
        bottomNavBackground = color.bottomNavBackground
        bottomNavItem = color.bottomNavItem
    }

    fun copy() = CustomColor(
        screenBackground,
        textColor,
        bottomNavBackground,
        bottomNavItem, dividerColor
    )
}
