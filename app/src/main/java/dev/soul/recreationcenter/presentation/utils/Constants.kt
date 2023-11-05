package dev.soul.recreationcenter.presentation.utils

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import dev.soul.recreationcenter.R
import dev.soul.recreationcenter.presentation.ui.theme.blueBackground1
import dev.soul.recreationcenter.presentation.ui.theme.blueBackground2
import dev.soul.recreationcenter.presentation.ui.theme.routeBackground1
import dev.soul.recreationcenter.presentation.ui.theme.routeBackground2
import dev.soul.recreationcenter.presentation.ui.theme.yellowBackground1
import dev.soul.recreationcenter.presentation.ui.theme.yellowBackground2

val weatherText = "+29°C"


val aboutText = "О базе отдыха"


val routeText = "Как добраться"

/**gradient*/
val routeGradient = Brush.horizontalGradient(
    colors = listOf(
        routeBackground1, routeBackground2
    )
)

val aboutGradient = Brush.horizontalGradient(
    colors = listOf(
        blueBackground1, blueBackground2
    )
)
val weatherGradient = Brush.horizontalGradient(
    colors = listOf(
        yellowBackground1, yellowBackground2
    )
)

/**icons*/

@DrawableRes
val routeIcon = R.drawable.ic_route

@DrawableRes
val backIcon = R.drawable.ic_round_back_button

@DrawableRes
val aboutIcon = R.drawable.ic_help

@DrawableRes
val weatherIcon = R.drawable.ic_sun


/**fonts*/
val roboto700 = FontFamily(Font(R.font.roboto_700, FontWeight.Normal))

val customFont = FontFamily(
    Font(R.font.roboto_500, FontWeight.Normal)
)

val roboto400 = FontFamily(Font(R.font.roboto_400, FontWeight.Normal))
val roboto500 = FontFamily(Font(R.font.roboto_500, FontWeight.Normal))
