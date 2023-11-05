package dev.soul.recreationcenter.domain.usecase.main

import dev.soul.recreationcenter.domain.repository.MainRepository
import dev.soul.recreationcenter.domain.usecase.blog.GetBlogListUseCase
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class GetMainUseCase @Inject constructor(
    private val mainRepository: MainRepository,
    private val getBlogList: GetBlogListUseCase
) {

    suspend operator fun invoke() = mainRepository.getMain()
}