package com.example.codelabs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    // menggunakan query pada umumnya untuk memanggil seluruh data di urutkan
    // menggunakan ascending/naik
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    // menambahkan flow untuk mengamati perubahan data
    fun getAlphabetizedWords(): Flow<List<Word>>

    // fungsi suspend untuk menangguhkan ketika menyisipkan kata

    // onConflictStrategy.IGNORE digunakan ketika ada ada konflik berupa kata
    // yang sama, nantinya kata tersebut akan diabaikan
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Word)

    // digunakan untuk menghapus seluruh data, menggunakan query karena tidak
    // ada anotasi spesifik untuk menghapus beberapa entity
    @Query("DELETE FROM word_table")
    suspend fun deleteAll()
}