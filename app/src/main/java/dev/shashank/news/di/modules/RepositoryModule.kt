package dev.shashank.news.di.modules

import dev.shashank.news.data.repository.NewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { NewsRepository(get(), get()) }
}
