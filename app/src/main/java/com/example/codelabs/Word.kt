package com.example.codelabs

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Membuat Entity untuk database dengan data class, menamai tabelnya dengan
// nama word_table dengan kolom word
@Entity(tableName = "word_table")
data class Word (@PrimaryKey @ColumnInfo(name = "word") val word: String)