package dev.soul.recreationcenter.domain.model.blog

data class BlogListModel(
    val date: DateModel,
    val id: Int,
    val image: ImageModel,
    val like: Int,
    val subtitle: String,
    val title: String,
    val view: Int
)