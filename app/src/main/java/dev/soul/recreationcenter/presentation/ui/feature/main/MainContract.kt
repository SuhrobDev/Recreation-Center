//package dev.soul.recreationcenter.presentation.ui.screen.main
//
//import dev.soul.recreationcenter.domain.model.blog.BlogListModel
//import dev.soul.recreationcenter.presentation.ui.base.ViewEvent
//import dev.soul.recreationcenter.presentation.ui.base.ViewSideEffect
//import dev.soul.recreationcenter.presentation.ui.base.ViewState
//
//class MainContract {
//
//    sealed class Event : ViewEvent {
//        object Retry : Event()
//        data class UserSelection(val user: BlogListModel) : Event()
//    }
//
//    data class State(
//        val users: List<BlogListModel>,
//        val isLoading: Boolean,
//        val isError: Boolean,
//    ) : ViewState
//
//    sealed class Effect : ViewSideEffect {
//        object DataWasLoaded : Effect()
//
//        sealed class Navigation : Effect() {
//            data class ToRepos(val userId: String) : Navigation()
//        }
//    }
//
//}
