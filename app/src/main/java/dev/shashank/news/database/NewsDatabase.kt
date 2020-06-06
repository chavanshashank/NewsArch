package dev.shashank.news.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NewsArticle::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDbDao(): NewsDatabaseDao

    companion object {
        private const val DB_NAME = "news-db"

        fun getInstance(context: Context) = Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
