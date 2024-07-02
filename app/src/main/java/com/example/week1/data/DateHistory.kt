package com.example.week1.data


data class History(
        var year: Int,
        var month: Int,
        var day: Int,
        var exercise: Int
)


data class DateHistory(
        var history: MutableList<History>
        )