package dev.shashank.news

import android.app.Application
import dev.shashank.news.di.KoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NewsApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()

            // use the Android context given there
            androidContext(this@NewsApp)

            // load properties from assets/koin.properties file
            androidFileProperties()

            // module list
            modules(KoinModules.modules)
        }
    }
}
