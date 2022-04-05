package com.example.codelabs

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

// mendeklarasikan DAO sebagai properti di constructor, lalu menerima DAO
// karena hanya membutuhkan akses ke DAO saja tanpa perlu keseluruh database
class WordRepository(private val wordDao: WordDao) {

    // flow akan memberi notifikasi ke observer apabila ada perubahan data
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    // disini secara default room menjalankan suspend queries diluar main thread
    // karna itu kita tidak perlu implementasi apa-apa untuk memastikan kita
    // tidak melakukan pekerjaan panjang/lama pada database di luar main thread
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word){
        wordDao.insert(word)
    }
}