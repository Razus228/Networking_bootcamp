package de.appsfactory.lecture.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import de.appsfactory.lecture.network.ApiComment
import de.appsfactory.lecture.network.R

const val commentsScreenArgPostId = "postId"
const val commentsScreenRouteDefinition = "comments/{$commentsScreenArgPostId}"
fun commentsScreenRoute(postId: Long) = "comments/$postId"

@Composable
fun CommentsScreen(
    onUpClick: () -> Unit,
    viewModel: CommentsViewModel = viewModel(),
) {
    val comments by viewModel.comments.collectAsStateWithLifecycle()
    CommentsScreen(
        comments = comments,
        onUpClick = onUpClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsScreen(
    comments: List<ApiComment>,
    onUpClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            navigationIcon = {
                IconButton(onClick = onUpClick) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = "",
                    )
                }
            },
            title = { Text(stringResource(id = R.string.comments_title)) },
            modifier = Modifier.fillMaxWidth(),
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(comments) { post ->
                Text(
                    text = post.body,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CommentsScreenPreview() {
    MaterialTheme {
        CommentsScreen(
            comments = List(10) {
                ApiComment(
                    body = "My body"
                )
            },
            onUpClick = {},
        )
    }
}