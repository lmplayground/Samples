package com.hamburger.room.data

import android.content.Context
import android.os.SystemClock
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hamburger.room.R
import com.pantos27.boringlauncher.utils.runOnIoThread
import java.io.InputStreamReader



/**
 * The Room database for this app
 */
@Database(entities = [Emoji::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun emojiDao(): EmojiDao

    companion object {
        private const val TAG = "AppDatabase"
        private const val DATABASE_NAME = "emojis"
        val listType = object : TypeToken<ArrayList<Emoji>>(){}.type

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and populate the database
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Log.d(TAG,"onCreate")
                        runOnIoThread {
                            context.resources.openRawResource(R.raw.emojis).use {
                                val inputStreamReader = InputStreamReader(it)
                                val gson = Gson()
                                val list = gson.fromJson<List<Emoji>>(inputStreamReader, listType)

                                list.forEach { emoji->
                                    instance?.emojiDao()?.insert(emoji)
                                    SystemClock.sleep(100)
                                }
                            }
                        }
                    }
                })
                .build()
        }


    }
}
