package dev.shashank.news.di

import dev.shashank.news.di.modules.apiServiceModule
import dev.shashank.news.di.modules.databaseModule
import dev.shashank.news.di.modules.repositoryModule
import dev.shashank.news.di.modules.viewModelModule

object KoinModules {
    val modules = listOf(
        databaseModule,
        viewModelModule,
        apiServiceModule,
        repositoryModule
    )
}
