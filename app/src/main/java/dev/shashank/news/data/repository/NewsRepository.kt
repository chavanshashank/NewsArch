package dev.shashank.news.data.repository

import dev.shashank.news.data.api.NewsService
import dev.shashank.news.data.database.asDomainModel
import dev.shashank.news.data.database.dao.NewsDatabaseDao
import dev.shashank.news.model.Article
import dev.shashank.news.model.ViewState
import dev.shashank.news.model.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NewsRepository(
    private val newsService: NewsService,
    private val newsDao: NewsDatabaseDao
) {

    fun getNewsArticles(): Flow<ViewState<List<Article>>> {
        return flow {

            emit(ViewState.loading())

            emit(ViewState.success(newsDao.getNewsArticles().asDomainModel()))

            newsDao.insertArticles(newsService.getTopHeadlinesHackerNews().articles.asDatabaseModel())

            emit(ViewState.success(newsDao.getNewsArticles().asDomainModel())) // stores duplicate if app relaunched.
        }.catch {

            emit(ViewState.error(it.message.orEmpty()))
        }.flowOn(Dispatchers.IO)
    }
}
