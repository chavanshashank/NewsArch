package dev.shashank.news.di

import dev.shashank.news.viewmodels.NewsArticleViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinModules {
    val modules = listOf(
        viewModelModule,
        databaseModule,
        apiServiceModule,
        repositoryModule
    )
}

val viewModelModule = module {
    viewModel { NewsArticleViewModel() }
}

val databaseModule = module {
}

val apiServiceModule = module {
}

val repositoryModule = module {
}
