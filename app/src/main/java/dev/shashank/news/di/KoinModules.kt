package dev.shashank.news.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.shashank.news.api.NewsService
import dev.shashank.news.database.NewsDatabase
import dev.shashank.news.repository.NewsRepository
import dev.shashank.news.viewmodels.NewsArticleViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://newsapi.org/v2/"

object KoinModules {
    val modules = listOf(
        databaseModule,
        viewModelModule,
        apiServiceModule,
        repositoryModule
    )
}

val viewModelModule = module {
    viewModel { NewsArticleViewModel(get()) }
}

val databaseModule = module {
    single { NewsDatabase.getInstance(androidApplication()) }

    single { get<NewsDatabase>().newsDbDao() }
}

val repositoryModule = module {
    single { NewsRepository(get(), get()) }
}

val apiServiceModule = module {

    single { moshi() }

    single { retrofit(get()) }

    single { get<Retrofit>().create(NewsService::class.java) }
}

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */

private fun moshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}

private fun retrofit(moshi: Moshi): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}
