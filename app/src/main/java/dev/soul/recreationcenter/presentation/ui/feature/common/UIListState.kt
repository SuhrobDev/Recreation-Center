package dev.soul.recreationcenter.presentation.ui.feature.common

data class UIListState<T>(
    val isLoading: Boolean = false,
    val data: List<T>? = null,
    val error: String = ""
)