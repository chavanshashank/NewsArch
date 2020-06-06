package dev.shashank.news.api

import dev.shashank.news.BuildConfig
import dev.shashank.news.model.NewsResponse
import retrofit2.http.GET

interface NewsService {
    @GET("top-headlines?sources=hacker-news&apiKey=${BuildConfig.NEWS_API_KEY}")
    suspend fun getTopHeadlinesHackerNews(): NewsResponse
}
