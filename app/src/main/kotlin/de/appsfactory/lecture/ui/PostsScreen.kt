package de.appsfactory.lecture.ui

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import de.appsfactory.lecture.network.ApiPost
import de.appsfactory.lecture.network.R

const val postsScreenRouteDefinition = "posts"

@Composable
fun PostsScreen(
    onPostClick: (ApiPost) -> Unit,
    viewModel: PostsViewModel = viewModel(),
) {
    val posts by viewModel.posts.collectAsStateWithLifecycle()
    PostsScreen(
        posts = posts,
        onPostClick = onPostClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(
    posts: List<ApiPost>,
    onPostClick: (ApiPost) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            title = { Text(stringResource(id = R.string.posts_title)) },
            modifier = Modifier.fillMaxWidth(),
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(posts, key = { it.id }) { post ->
                Column(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .clickable { onPostClick(post) }
                        .padding(16.dp)
                ) {
                    Text(
                        text = post.title,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = post.body,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PostsScreenPreview() {
    MaterialTheme {
        PostsScreen(
            posts = List(10) {
                ApiPost(
                    id = it.toLong(),
                    title = "My Title",
                    body = "My body"
                )
            },
            onPostClick = {},
        )
    }
}