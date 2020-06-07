package dev.shashank.news.di.modules

import dev.shashank.news.ui.viewmodels.NewsArticleViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NewsArticleViewModel(get()) }
}
