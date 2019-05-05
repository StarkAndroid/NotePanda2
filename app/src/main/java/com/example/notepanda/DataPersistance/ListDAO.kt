package com.example.notepanda.DataPersistance

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ListDAO {

    @Insert
    fun insertList(vararg listEntity: ListEntity)

    @Delete
    fun deleteList(listEntity: ListEntity)

    @Query("SELECT * FROM Lists ORDER BY list_name")
    fun loadListsOrderedAlphabetically(): LiveData<List<ListEntity>>

    @Query("SELECT * FROM Lists ORDER BY list_creation_date DESC")
    fun loadListsOrderedByCreationDate(): LiveData<List<ListEntity>>

    @Query("SELECT * FROM Lists WHERE listID = :id")
    fun queryByID(id: Int) : ListEntity

}