package dev.shashank.news.model

import dev.shashank.news.data.database.NewsArticle

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)

data class Article( // used as domain as well as network model class, can be separated.
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)

fun List<Article>.asDatabaseModel(): List<NewsArticle> {
    return map { article ->
        NewsArticle(
            author = article.author,
            content = article.content,
            description = article.description,
            publishedAt = article.publishedAt,
            title = article.title,
            urlToImage = article.urlToImage,
            url = article.url
        )
    }
}
