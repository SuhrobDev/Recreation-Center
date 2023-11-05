package dev.soul.recreationcenter.presentation.ui.feature.common

data class UIObjectState<T>(
    val error: String = "",
    val data: T? = null,
    val isLoading: Boolean = false
)