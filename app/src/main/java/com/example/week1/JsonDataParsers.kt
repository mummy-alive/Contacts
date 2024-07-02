package com.example.week1

import android.content.Context
import android.util.JsonWriter
import com.example.week1.data.DateHistory
import com.example.week1.data.History
import com.example.week1.data.Person
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.IOException
import android.util.Log
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

fun readJsonFileFromInternalStorage(context: Context, fileName: String): String {
    return try {
        val file = File(context.filesDir, fileName)
        file.readText()
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        ""
    }
}

fun writeJsonFileToInternalStorage(context: Context, fileName: String, jsonString: String) {
    try {
        val file = File(context.filesDir, fileName)
        file.writeText(jsonString)
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
    return gson.fromJson(jsonString, listType)
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
        if (historyItem != null) {
            historyItem.exercise = newExerciseValue
        } else {
            // Create a new History object and add it to the list
            val newHistory = History(year, month, day, newExerciseValue)
            dateHistory.history.add(newHistory)
        }
        return gson.toJson(dateHistory)
    }
}


fun copyJsonFileFromAssetsIfNeeded(context: Context, fileName: String) {
    val file = File(context.filesDir, fileName)
    if (!file.exists()) {
        try {
            context.assets.open(fileName).use { inputStream ->
                file.outputStream().use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            Log.d("JSON", "File copied successfully: ${file.path}")
        } catch (e: IOException) {
            Log.e("JSON", "Failed to copy file: ${e.message}")
        }
    } else {
        Log.d("JSON", "File already exists: ${file.path}")
    }
}
