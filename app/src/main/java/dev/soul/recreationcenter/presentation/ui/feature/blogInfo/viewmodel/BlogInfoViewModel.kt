package dev.soul.recreationcenter.presentation.ui.feature.blogInfo.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.soul.recreationcenter.domain.Resource
import dev.soul.recreationcenter.domain.model.blogInfo.BlogInfoModel
import dev.soul.recreationcenter.domain.usecase.blogInfo.BlogInfoUseCase
import dev.soul.recreationcenter.presentation.ui.feature.common.UIObjectState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogInfoViewModel @Inject constructor(
    private val blogInfoUseCase: BlogInfoUseCase, savedStateHandle: SavedStateHandle,
) :
    ViewModel() {


//    init {
////        val argument = savedStateHandle.get<String>(BLOG_ARGUMENT_KEY).orEmpty()
////        Log.d("KJDBASDBA", "argument in viewmodel: $argument")
//        getBlogInfo(223)
//    }

    private val _blogInfoState = MutableStateFlow(UIObjectState<BlogInfoModel>())
    val blogInfoState = _blogInfoState.asStateFlow()

    fun getBlogInfo(blogId: Int) = viewModelScope.launch {
        blogInfoUseCase.getBlogInfoUseCase(blogId).collectLatest {
            when (it) {
                is Resource.Loading -> {
                    _blogInfoState.value = UIObjectState(isLoading = true)
                }

                is Resource.Success -> {
                    _blogInfoState.value = UIObjectState(data = it.data)
                }

                is Resource.Error -> {
                    _blogInfoState.value = UIObjectState(error = it.message.toString())
                }

            }
        }
    }

}