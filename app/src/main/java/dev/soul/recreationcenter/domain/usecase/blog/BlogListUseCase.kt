package dev.soul.recreationcenter.domain.usecase.blog

import javax.inject.Inject

data class BlogListUseCase @Inject constructor(val getBlogList: GetBlogListUseCase)