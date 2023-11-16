package de.appsfactory.lecture.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = postsScreenRouteDefinition,
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None },
                            modifier = Modifier
                        .background(MaterialTheme.colorScheme.background),

                ) {
                    composable(postsScreenRouteDefinition) {
                        PostsScreen(
                            onPostClick = { post ->
                                navController.navigate(commentsScreenRoute(post.id))
                            }
                        )
                    }
                    composable(
                        commentsScreenRouteDefinition,
                        arguments = listOf(
                            navArgument(commentsScreenArgPostId) { type = NavType.LongType }
                        )
                    ) {
                        CommentsScreen(onUpClick = { navController.navigateUp() })
                    }
                }
            }
        }
    }
}