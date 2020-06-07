package dev.shashank.news.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dev.shashank.news.data.repository.NewsRepository
import dev.shashank.news.model.Article
import dev.shashank.news.model.ViewState

class NewsArticleViewModel(
    newsRepository: NewsRepository
) : ViewModel() {

    private val newsArticles: LiveData<ViewState<List<Article>>> =
        newsRepository.getNewsArticles().asLiveData()

    fun getNewsArticles(): LiveData<ViewState<List<Article>>> = newsArticles
}
