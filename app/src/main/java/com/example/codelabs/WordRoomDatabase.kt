package com.example.codelabs

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// exportschema diberikan false supaya menghindari peringatan build
@Database (entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        // menentukan singleton, untuk mencegah beberapa instance database
        // dibuka bersamaan
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        // disini akan membuat singleton, dimana akan membuat database saat
        // pertama kali dkases
        fun getDatabase(context: Context): WordRoomDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}