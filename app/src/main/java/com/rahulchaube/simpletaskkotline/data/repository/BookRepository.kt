package com.rahulchaube.simpletaskkotline.data.repository

import androidx.lifecycle.LiveData
import com.rahulchaube.simpletaskkotline.data.dao.BookDao
import com.rahulchaube.simpletaskkotline.data.entity.Book

class BookRepository(private val bookDao: BookDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Book>> = bookDao.getBookList()

    suspend fun insert(word: Book) {
        bookDao.insert(word)
    }
}