package dev.soul.recreationcenter.presentation.utils

import androidx.annotation.StringRes
import dev.soul.recreationcenter.App

fun getString(@StringRes int: Int) = App.resources.getString(int)

fun String.dateConvert() = this.replace('-', '.').substringBefore('T')