package dev.soul.recreationcenter.domain.usecase.blog

import dev.soul.recreationcenter.domain.repository.MainRepository
import javax.inject.Inject

class GetBlogListUseCase @Inject constructor(private val mainRepository: MainRepository) {
    suspend operator fun invoke() = mainRepository.getBlogList()
}