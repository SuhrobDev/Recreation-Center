package dev.soul.recreationcenter.data.remote.dto.blogInfo

data class BlogInfoDto(
    val content: String,
    val date: String,
    val id: Int,
    val image: ImageBlogDto,
    val like: Int,
    val subtitle: String,
    val title: String,
    val url: String
)