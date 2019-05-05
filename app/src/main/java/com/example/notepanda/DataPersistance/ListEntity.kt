package com.example.notepanda.DataPersistance

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Lists")
data class ListEntity(

    @PrimaryKey(autoGenerate = true) var listID: Int,
    @ColumnInfo(name = "list_name") var listName: String?,
    @ColumnInfo(name = "list_description") var listDescription: String?,
    @ColumnInfo(name = "list_creation_date") var listCreationDate: Long
)