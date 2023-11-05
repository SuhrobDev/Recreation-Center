package dev.soul.recreationcenter.domain.model.blogInfo

data class BlogInfoModel(
    val content: String,
    val date: String,
    val id: Int,
    val image: ImageInfoModel,
    val like: Int,
    val subtitle: String,
    val title: String,
    val url: String
)