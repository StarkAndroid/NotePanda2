package com.example.notepanda.Utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun hideSoftKeyBoard(context: Context?, view: View?) {
        try {
          val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
          imm.hideSoftInputFromWindow(view?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
          e.printStackTrace()
          }
        }
    fun getCurrentDate(): Long {
        val current  = Date()
        return current.time
    }
    fun ConvertTimestampToDate(timestamp: Long?): Date{
        return Date(timestamp?:100)
    }
    fun convertDateToNormalDate(date: Date):String{
        val formatter = SimpleDateFormat("yyyy-MM-dd-hh:mm")
        return formatter.format(date)
    }
}