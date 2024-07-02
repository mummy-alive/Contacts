package com.example.week1

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.example.week1.data.DateHistory
import com.example.week1.data.History

@Composable
fun HeatmapCalendar() {
    val context = LocalContext.current
    val jsonString = readJsonFileFromInternalStorage(context, "exerciseHistory.json")
    val exerciseHistory: DateHistory = parseJsonToDateHistory(jsonString)
    LazyHorizontalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .height(147.dp),
        rows = GridCells.Fixed(7)
    ) {
        items(exerciseHistory.history.size) { date ->
            DayBox(exerciseHistory.history[date])
        }
    }
}

@Composable
fun DayBox(history: History, max: Int=255) {
    val color = calculateColor(history.exercise, max)

    Box(
        modifier = Modifier
            .padding(1.dp)
            .border(0.5.dp, Color.Transparent, RoundedCornerShape(3.dp))
            .size(20.dp)
            .aspectRatio(1.0f)
            .background(color)
    ) {
    }
}

@Composable
private fun calculateColor(intenseAmount: Int, max: Int): Color {
    val darkness = if (max > 0) intenseAmount.toFloat() / max else 0f
    return if (intenseAmount == 0) {
        Color.LightGray
    } else {
        Color(0f, (1 - darkness).coerceIn(0f, 1f), 0f)
    }
}
