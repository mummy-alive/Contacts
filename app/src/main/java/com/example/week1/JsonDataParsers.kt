package com.example.week1

import android.content.Context
import com.example.week1.data.DateHistory
import com.example.week1.data.Person
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

fun readJsonFile(context: Context, fileName: String): String {
    return try {
        context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        ""
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