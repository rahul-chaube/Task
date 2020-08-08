package com.rahulchaube.simpletaskkotline.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rahulchaube.simpletaskkotline.data.entity.Book

@Dao
interface BookDao {

    @Query("SELECT * from book ORDER BY id ASC")
    fun getBookList(): LiveData<List<Book>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(book: Book)

    @Query("DELETE FROM book")
    suspend fun deleteAll()
}