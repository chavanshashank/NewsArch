package dev.shashank.news.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.shashank.news.data.database.NewsArticle

@Dao
interface NewsDatabaseDao {

    @Insert
    fun insertArticles(articles: List<NewsArticle>): List<Long>

    @Query("SELECT * FROM news_articles")
    suspend fun getNewsArticles(): List<NewsArticle>
}
