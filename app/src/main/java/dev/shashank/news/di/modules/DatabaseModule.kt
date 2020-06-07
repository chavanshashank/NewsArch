package dev.shashank.news.di.modules

import dev.shashank.news.data.database.NewsDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single { NewsDatabase.getInstance(androidApplication()) }

    single { get<NewsDatabase>().newsDbDao() }
}
