package dev.shashank.news.di.modules

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.shashank.news.data.api.NewsService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

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
    return Retrofit.Builder().baseUrl(NewsService.API_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}
