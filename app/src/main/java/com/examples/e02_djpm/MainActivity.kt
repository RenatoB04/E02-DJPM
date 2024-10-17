package com.examples.e02_djpm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.examples.e02_djpm.ui.ArticleDetail
import com.examples.e02_djpm.ui.HomeView
import com.examples.e02_djpm.ui.theme.E02DJPMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            E02DJPMTheme {
                var navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController = navController,
                        startDestination = Screen.Home.route ) {
                        composable(route = Screen.Home.route) {
                            HomeView(
                                navController = navController,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        composable(route = Screen.ArticleDetail.route) {
                            val url = it.arguments?.getString("articleUrl")
                            ArticleDetail(
                                modifier = Modifier.padding(innerPadding),
                                url = url ?: ""
                            )
                        }
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object ArticleDetail : Screen("article_detail/{articleUrl}")

}