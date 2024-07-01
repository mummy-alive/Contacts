package com.example.week1.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.week1.R
import org.json.JSONObject
import java.io.InputStream

data class Person(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    @StringRes val tel: Int
)

val people = listOf(
    Person(R.drawable.anyujin, R.string.person_name_1, R.string.person_phone_1),
    Person(R.drawable.anyujin, R.string.person_name_2, R.string.person_phone_2),
    Person(R.drawable.anyujin, R.string.person_name_3, R.string.person_phone_3)
)