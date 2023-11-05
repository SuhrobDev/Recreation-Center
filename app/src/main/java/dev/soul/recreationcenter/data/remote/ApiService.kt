package dev.soul.recreationcenter.data.remote

import dev.soul.recreationcenter.data.remote.dto.MainResponse
import dev.soul.recreationcenter.data.remote.dto.blog.BlogListDto
import dev.soul.recreationcenter.data.remote.dto.blogInfo.BlogInfoDto
import dev.soul.recreationcenter.data.remote.dto.main.HomeDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(Endpoints.GET_MAIN)
    suspend fun getMain(@Query("id") id: Int): MainResponse<HomeDto>

    @GET(Endpoints.GET_BLOG_INFO)
    suspend fun getBlogById(
        @Query("id") id: Int,
        @Query("blog_id") blogId: Int
    ): MainResponse<BlogInfoDto>


    @GET(Endpoints.GET_BLOG_LIST)
    suspend fun getBlogList(
        @Query("id") id: Int,
        @Query("format") format: String
    ): MainResponse<List<BlogListDto>>
}