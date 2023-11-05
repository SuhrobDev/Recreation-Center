package dev.soul.recreationcenter.data.remote.dto.blog

data class BlogListDto(
    val date: DateDto,
    val id: Int,
    val image: ImageDto,
    val like: Int,
    val subtitle: String,
    val title: String,
    val view: Int
)