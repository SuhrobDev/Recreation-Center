package dev.soul.recreationcenter.data.repository

import dev.soul.recreationcenter.data.common.FLOW_RESOURCE
import dev.soul.recreationcenter.data.common.ResultHandler
import dev.soul.recreationcenter.data.mapper.toModel
import dev.soul.recreationcenter.data.remote.ApiService
import dev.soul.recreationcenter.domain.Resource
import dev.soul.recreationcenter.domain.model.blog.BlogListModel
import dev.soul.recreationcenter.domain.model.blogInfo.BlogInfoModel
import dev.soul.recreationcenter.domain.model.main.HomeModel
import dev.soul.recreationcenter.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val apiService: ApiService) : MainRepository,
    ResultHandler() {

    override suspend fun getMain(): FLOW_RESOURCE<HomeModel> = loadResult({
        apiService.getMain(117)
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.toModel()))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun getBlogInfo(blogId: Int): FLOW_RESOURCE<BlogInfoModel> = loadResult({
        apiService.getBlogById(id = 117, blogId = blogId)
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.toModel()))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })

    override suspend fun getBlogList(): FLOW_RESOURCE<List<BlogListModel>> = loadResult({
        apiService.getBlogList(id = 117, format = "card")
    }, { data, flow ->
        try {
            flow.emit(Resource.Success(data.map { it.toModel() }))
        } catch (e: Exception) {
            flow.emit(Resource.Error(e.message.toString()))
        }
    })
}