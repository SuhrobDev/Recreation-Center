package dev.soul.recreationcenter.domain.usecase.main

import javax.inject.Inject

data class MainUseCase @Inject constructor(
    val getMainUseCase: GetMainUseCase
)