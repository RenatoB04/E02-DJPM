package com.examples.e02_djpm.ui

import androidx.lifecycle.ViewModel
import com.examples.e02_djpm.models.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

data class ArticlesState (
    val articles: ArrayList<Article> = arrayListOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ArticlesState())
    val uiState : StateFlow<ArticlesState> = _uiState.asStateFlow()

    fun fetchArticles() {

        _uiState.value = ArticlesState(
            isLoading = true,
            error = null)

        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://newsapi.org/v2/top-headlines?country=us&apiKey=5b3a3c6e671c49fda093c7546e3ee7ae")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                _uiState.value = ArticlesState(
                    isLoading = true,
                    error = e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val articlesResult = arrayListOf<Article>()
                    val result = response.body!!.string()
                    val jsonResult = JSONObject(result)
                    val status = jsonResult.getString("status")
                    if (status == "ok") {
                        val articlesJson = jsonResult.getJSONArray("articles")
                        for (index in 0 until articlesJson.length()) {
                            val articleJson = articlesJson.getJSONObject(index)
                            val article = Article.fromJson(articleJson)
                            articlesResult.add(article)
                        }
                    }
                    _uiState.value = ArticlesState(
                        articles = articlesResult,
                        isLoading = false,
                        error = null)
                }
            }
        })
    }

}