package dev.soul.recreationcenter.data.mapper

import dev.soul.recreationcenter.data.remote.dto.blog.BlogListDto
import dev.soul.recreationcenter.data.remote.dto.blog.DateDto
import dev.soul.recreationcenter.data.remote.dto.blog.ImageDto
import dev.soul.recreationcenter.data.remote.dto.blogInfo.BlogInfoDto
import dev.soul.recreationcenter.data.remote.dto.blogInfo.ImageBlogDto
import dev.soul.recreationcenter.data.remote.dto.main.Button
import dev.soul.recreationcenter.data.remote.dto.main.Content
import dev.soul.recreationcenter.data.remote.dto.main.HomeDto
import dev.soul.recreationcenter.data.remote.dto.main.Template
import dev.soul.recreationcenter.domain.model.blog.BlogListModel
import dev.soul.recreationcenter.domain.model.blog.DateModel
import dev.soul.recreationcenter.domain.model.blog.ImageModel
import dev.soul.recreationcenter.domain.model.blogInfo.BlogInfoModel
import dev.soul.recreationcenter.domain.model.blogInfo.ImageInfoModel
import dev.soul.recreationcenter.domain.model.main.ButtonModel
import dev.soul.recreationcenter.domain.model.main.ContentModel
import dev.soul.recreationcenter.domain.model.main.HomeModel
import dev.soul.recreationcenter.domain.model.main.TemplateModel

fun Content.toModel(): ContentModel {
    return ContentModel(template = template.toModel(), title = title, url = url)
}

fun Template.toModel(): TemplateModel {
    return TemplateModel(card = card, direction, `object`, size)
}

fun BlogListDto.toModel(): BlogListModel {
    return BlogListModel(
        date = date.toModel(),
        id = id,
        image = image.toModel(),
        like,
        subtitle,
        title,
        view
    )
}

fun DateDto.toModel(): DateModel {
    return DateModel(date, typeDate)
}

fun ImageDto.toModel(): ImageModel {
    return ImageModel(lg, md, sm)
}


fun HomeDto.toModel(): HomeModel {
    return HomeModel(buttons.map { it.toModel() }, content.map { it.toModel() })
}

fun Button.toModel(): ButtonModel {
    return ButtonModel(color, icon, title, type, url)
}

fun BlogInfoDto.toModel(): BlogInfoModel {
    return BlogInfoModel(content, date, id, image.toModel(), like, subtitle, title, url)
}

fun ImageBlogDto.toModel(): ImageInfoModel {
    return ImageInfoModel(lg, md, sm)
}