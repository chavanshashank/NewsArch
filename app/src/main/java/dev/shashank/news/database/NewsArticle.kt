package dev.shashank.news.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.shashank.news.model.Article

@Entity(tableName = "news_articles")
data class NewsArticle(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "author")
    val author: String?,

    @ColumnInfo(name = "content")
    val content: String?,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "publishedAt")
    val publishedAt: String?,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "urlToImage")
    val urlToImage: String?
)

fun List<NewsArticle>.asDomainModel(): List<Article> {
    return map { newsArticle ->
        Article(
            author = newsArticle.author,
            content = newsArticle.content,
            description = newsArticle.description,
            publishedAt = newsArticle.publishedAt,
            title = newsArticle.title,
            urlToImage = newsArticle.urlToImage,
            url = newsArticle.url
        )
    }
}
