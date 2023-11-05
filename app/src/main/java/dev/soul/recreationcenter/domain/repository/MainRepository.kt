package dev.soul.recreationcenter.domain.repository

import dev.soul.recreationcenter.data.common.FLOW_RESOURCE
import dev.soul.recreationcenter.domain.model.blog.BlogListModel
import dev.soul.recreationcenter.domain.model.blogInfo.BlogInfoModel
import dev.soul.recreationcenter.domain.model.main.HomeModel

interface MainRepository {

    suspend fun getMain(): FLOW_RESOURCE<HomeModel>

    suspend fun getBlogInfo(blogId: Int): FLOW_RESOURCE<BlogInfoModel>

    suspend fun getBlogList(): FLOW_RESOURCE<List<BlogListModel>>
}