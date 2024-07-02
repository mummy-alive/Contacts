package com.example.week1

import android.content.Context
import android.util.JsonWriter
import com.example.week1.data.DateHistory
import com.example.week1.data.History
import com.example.week1.data.Person
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.OutputStream
import java.io.OutputStreamWriter


fun readJsonFile(context: Context, fileName: String): String {
    return try {
        context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        ""
    }
}

fun writeJsonFile(context: Context, fileName: String, jsonString: String) {
    try {
        context.openFileOutput(fileName, Context.MODE_PRIVATE).use { output ->
            output.write(jsonString.toByteArray())
        }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
    }
}

fun parseJsonToPeople(jsonString: String): List<Person> {
    val gson = Gson()
    val listType = object : TypeToken<List<Person>>() {}.type
    val people: List<Person> = gson.fromJson(jsonString, listType)
    return people.sortedBy { it.name }
}

fun parseJsonToDateHistory(jsonString: String): DateHistory {
    val gson = Gson()
    val listType = object : TypeToken<DateHistory>() {}.type
    val exerciseHistory: DateHistory = gson.fromJson(jsonString, listType)
    return exerciseHistory
}

fun modifyDateHistoryValue(
    jsonString: String,
    year: Int,
    month: Int,
    day: Int,
    newExerciseValue: Int
): String {
    val gson = Gson()
    val dateHistory = parseJsonToDateHistory(jsonString)
    dateHistory.let {
        val historyItem = it.history.find { history ->
            (history.year == year) && (history.month == month) && (history.day == day)
        }
        historyItem?.exercise = newExerciseValue
        return gson.toJson(dateHistory)
    }
    return jsonString
}

