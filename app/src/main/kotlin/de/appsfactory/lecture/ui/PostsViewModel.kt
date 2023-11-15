package de.appsfactory.lecture.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.appsfactory.lecture.network.ApiPost
import de.appsfactory.lecture.network.jsonPlaceholderApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostsViewModel: ViewModel() {

    private val _posts: MutableStateFlow<List<ApiPost>> = MutableStateFlow(emptyList())
    val posts: StateFlow<List<ApiPost>> = _posts.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val postsResponse = jsonPlaceholderApi.getPosts()
            val responseBody = postsResponse.body()

            _posts.update {
                if(postsResponse.isSuccessful && responseBody != null) {
                    responseBody
                } else {
                    emptyList()
                }
            }
        }
    }
}