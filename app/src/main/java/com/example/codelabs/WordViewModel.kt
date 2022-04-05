package com.example.codelabs

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class WordViewModel(private val repository: WordRepository): ViewModel(){

    // ketika menggunakan LiveData saat caching allWords mempunyai keuntungan
    // berupa kita dapat menaruh observer ke dalam data dan hanya memperbarui
    // UI ketika benar-benar terjadi perubahan data.

    // repository disini seluruhnya dipisahkan dari UI oleh ViewModel
    val allWords: LiveData<List<Word>> = repository.allWords.asLiveData()

    // menjalankan coroutine untuk memasukan data ke non-blocking way
    fun insert(word: Word) = viewModelScope.launch{
        repository.insert(word)
    }
}

class WordViewModelFactory(private val repository: WordRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WordViewModel::class.java)){
            @Suppress("UNCHEKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}