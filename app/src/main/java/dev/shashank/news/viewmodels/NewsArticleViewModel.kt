package dev.shashank.news.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dev.shashank.news.model.Article
import dev.shashank.news.model.ViewState
import dev.shashank.news.repository.NewsRepository

class NewsArticleViewModel(
    newsRepository: NewsRepository
) : ViewModel() {

    private val newsArticles: LiveData<ViewState<List<Article>>> =
        newsRepository.getNewsArticles().asLiveData()

    fun getNewsArticles(): LiveData<ViewState<List<Article>>> = newsArticles
}
