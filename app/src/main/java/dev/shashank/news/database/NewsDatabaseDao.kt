package dev.shashank.news.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsDatabaseDao {

    @Insert
    fun insertArticles(articles: List<NewsArticle>): List<Long>

    @Query("SELECT * FROM news_articles")
    suspend fun getNewsArticles(): List<NewsArticle>
}
