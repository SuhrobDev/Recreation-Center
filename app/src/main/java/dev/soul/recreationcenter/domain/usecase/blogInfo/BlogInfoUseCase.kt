package dev.soul.recreationcenter.domain.usecase.blogInfo

import javax.inject.Inject

data class BlogInfoUseCase @Inject constructor(
    val getBlogInfoUseCase: GetBlogInfoUseCase
)
