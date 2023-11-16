package de.appsfactory.lecture.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.appsfactory.lecture.network.ApiComment
import de.appsfactory.lecture.network.jsonPlaceholderApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CommentsViewModel(
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val postId = requireNotNull(savedStateHandle.get<Long>(commentsScreenArgPostId))

    private val _comments: MutableStateFlow<List<ApiComment>> = MutableStateFlow(emptyList())
    val comments: StateFlow<List<ApiComment>> = _comments.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val response = jsonPlaceholderApi.getComments(postId)
            val responseBody = response.body()

            _comments.update {
                if(response.isSuccessful && responseBody != null) {
                    responseBody
                } else {
                    emptyList()
                }
            }
        }
    }
}