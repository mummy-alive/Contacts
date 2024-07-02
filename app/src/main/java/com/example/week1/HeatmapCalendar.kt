package com.example.week1

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.example.week1.data.DateHistory
import com.example.week1.data.History
import java.lang.Float.min
import java.time.LocalDate
import kotlin.math.max

@Composable
fun HeatmapCalendar() {
    val boxSize: Int = 35
    val boxPad: Int = 3
    val context = LocalContext.current
    val jsonString = readJsonFileFromInternalStorage(context, "exerciseHistory.json")
    val exerciseHistory: DateHistory = parseJsonToDateHistory(jsonString)
    val currentDate: LocalDate = LocalDate.now()
    /*   var yearState by remember { mutableIntStateOf(currentDate.year) }
       var monthState by remember { mutableIntStateOf(currentDate.monthValue) }
       var dayState by remember { mutableIntStateOf(currentDate.dayOfMonth) }
       var dowState by remember { mutableStateOf(currentDate.dayOfWeek) }*/


    LazyHorizontalGrid(
        modifier = Modifier
            .height((35 * 7 + 3 * 6 + 20).dp),
        rows = GridCells.Fixed(7)
    ) {
        items(exerciseHistory.history.size) { date ->
            DayBox(exerciseHistory.history[date], boxSize = boxSize, boxPad = boxPad)
        }
    }
}


@Composable
fun DayBox(history: History, max: Int = 255, boxSize: Int, boxPad: Int) {
    val targetColor = Color(0xFF41C3E4) // Target color
    val ratio = if (max > 0) min(1f, history.exercise.toFloat() / max) else 0f
    val zeroColor = Color.White
    //val borderColor = if
    Box(
        modifier = Modifier
            .padding(boxPad.dp)
            .border(2.dp, Color.Transparent)
            .clip(shape = RoundedCornerShape(25))
            .size(boxSize.dp, boxSize.dp)
            .aspectRatio(1.0f)
            .background(
                if (history.exercise == 0) zeroColor.copy(alpha = 0.02f) else targetColor.copy(
                    alpha = max(0.02f, ratio)
                )
            )
        //.alpha(ratio)
    )
}