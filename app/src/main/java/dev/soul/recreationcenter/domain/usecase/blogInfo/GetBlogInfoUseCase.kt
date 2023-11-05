package dev.soul.recreationcenter.domain.usecase.blogInfo

import dev.soul.recreationcenter.domain.repository.MainRepository
import javax.inject.Inject

class GetBlogInfoUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(blogId: Int) = mainRepository.getBlogInfo(blogId)
}