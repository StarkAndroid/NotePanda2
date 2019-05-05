package com.example.notepanda.Utils

import androidx.room.TypeConverter
import java.sql.Date

class TimeConverter{
    @TypeConverter
    fun ConvertTimestampToDate(timestamp : Long): Date{
        return Date(timestamp)
    }
    @TypeConverter
    fun ConvertDateToTimestamp(date: Date):Long{
        return date.time
    }
}