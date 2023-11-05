package dev.soul.recreationcenter.data.common

import dev.soul.recreationcenter.data.remote.dto.MainResponse
import dev.soul.recreationcenter.domain.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

open class ResultHandler {
    protected suspend fun <T, K> loadResult(
        requestSource: suspend () -> MainResponse<T>,
        successBody: suspend (T, FlowCollector<Resource<K>>) -> Unit,
        offlineMode: suspend (FlowCollector<Resource<K>>) -> Unit = { Unit }
    ): Flow<Resource<K>> = withContext(Dispatchers.IO) {
        flow {
            try {
                emit(Resource.Loading())
                val response = requestSource.invoke()
                if (response.success) {
                    if (response.data != null) {
                        successBody.invoke(response.data, this)
                    } else {
                        emit(
                            Resource.Error(
                                "null data"
                            )
                        )
                    }
                } else {
                    emit(Resource.Error(response.data.toString()))
                }
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        "HttpException occurred: $e"
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        "Couldn't reach server. Please check your internet connection!"
                    )
                )
            }
        }
    }
}