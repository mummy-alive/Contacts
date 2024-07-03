package com.example.week1

import com.example.week1.data.History
import kotlin.math.max

fun MaxSteadyDay(exerciseHistory: List<History>): Int {
    var cnt: Int = 0
    var mx: Int = 0
    for(history in exerciseHistory){
        if(history.exercise >0 ){
            cnt++
            mx = max(mx, cnt)
        }else cnt = 0
    }
    return mx
}

fun ThisMonthExercise(exerciseHistory: List<History>, year: Int, month: Int): Int {
    var cnt: Int = 0
    for (history in exerciseHistory) {
        if (history.year==year){
            if(history.month ==month ){
                cnt += history.exercise
            }
            if(history.month > month)
                return cnt;
        }

    }
    return cnt
}

fun ThisWeekExercise(
    exerciseHistory: List<History>,
    year: Int,
    month: Int,
    day: Int,
    dow: Int
): Int {
    var cnt: Int = 0
    var todayIdx: Int = 0
    for (i: Int in 0..<exerciseHistory.size) {
        if (exerciseHistory[i].year == year &&
            exerciseHistory[i].month == month &&
            exerciseHistory[i].day == day
        ) todayIdx = i
    }
    for (i: Int in 1..dow)
        cnt += exerciseHistory[todayIdx - i + 1].exercise
    return cnt
}