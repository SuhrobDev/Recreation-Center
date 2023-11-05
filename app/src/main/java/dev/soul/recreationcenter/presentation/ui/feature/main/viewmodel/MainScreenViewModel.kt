package dev.soul.recreationcenter.presentation.ui.feature.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.soul.recreationcenter.domain.Resource
import dev.soul.recreationcenter.domain.model.blog.BlogListModel
import dev.soul.recreationcenter.domain.model.main.HomeModel
import dev.soul.recreationcenter.domain.usecase.blog.BlogListUseCase
import dev.soul.recreationcenter.domain.usecase.main.MainUseCase
import dev.soul.recreationcenter.presentation.ui.feature.common.UIListState
import dev.soul.recreationcenter.presentation.ui.feature.common.UIObjectState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val mainUseCase: MainUseCase,
    private val blogListUseCase: BlogListUseCase
) : ViewModel() {

    private val _mainState = MutableStateFlow(UIObjectState<HomeModel>())
    val mainState = _mainState.asStateFlow()

    private val _blogListState = MutableStateFlow(UIListState<BlogListModel>())
    val blogListState = _blogListState.asStateFlow()

    init {
        getMain()
    }

    private fun getMain() = viewModelScope.launch {
        mainUseCase.getMainUseCase().collectLatest {
            when (it) {
                is Resource.Loading -> {
                    _mainState.value = UIObjectState(isLoading = true)
                }

                is Resource.Success -> {
                    it.data?.content?.map {
                        if (it.template.`object` == "blog") getBlogList()
                    }
                    _mainState.value = UIObjectState(data = it.data)
                }

                is Resource.Error -> {
                    _mainState.value = UIObjectState(error = it.message.toString())
                }

            }
        }
    }

    private fun getBlogList() = viewModelScope.launch {
        blogListUseCase.getBlogList().collectLatest {
            when (it) {
                is Resource.Success -> {
                    _blogListState.value = UIListState(data = it.data)
                }

                is Resource.Loading -> _blogListState.value = UIListState(isLoading = true)
                is Resource.Error -> {
                    _blogListState.value =
                        UIListState(error = it.message.toString())
                }
            }
        }
    }
}