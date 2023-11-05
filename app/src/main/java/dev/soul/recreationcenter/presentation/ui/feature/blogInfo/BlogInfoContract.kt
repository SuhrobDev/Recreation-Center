//package dev.soul.recreationcenter.presentation.ui.screen.blogInfo
//
//import dev.soul.recreationcenter.domain.model.blogInfo.BlogInfoModel
//import dev.soul.recreationcenter.presentation.ui.base.ViewEvent
//import dev.soul.recreationcenter.presentation.ui.base.ViewSideEffect
//import dev.soul.recreationcenter.presentation.ui.base.ViewState
//
//class BlogInfoContract {
//
//    sealed class Event : ViewEvent {
//        object Retry : Event()
//        object BackButtonClicked : Event()
//    }
//
//    data class State(
//        val blogInfo: BlogInfoModel?,
//        val isUserLoading: Boolean,
//        val isReposLoading: Boolean,
//        val isError: Boolean,
//    ) : ViewState
//
//    sealed class Effect : ViewSideEffect {
//        sealed class Navigation : Effect() {
//            object Back : Navigation()
//        }
//    }
//
//}
