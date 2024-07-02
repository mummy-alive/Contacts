package com.example.week1.data

import androidx.annotation.DrawableRes

data class Person(
    val name: String,
    val tel: String,
    @DrawableRes var imageResourceId: Int,
    var recentExercise: String
)

