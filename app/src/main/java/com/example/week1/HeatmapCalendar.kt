package com.example.week1

import android.view.RoundedCorner
import android.view.Surface
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Surface

@Composable
fun HeatmapCalendar() {
    var exerciseHistory: List<Int> = /*(0..1000).toList()*/
        listOf(33, 50, 104, 0, 87, 300, 0, 130, 65, 91, 33, 50, 104, 0, 87, 300, 0, 130, 65, 91,
            33, 50, 104, 0, 87, 300, 0, 130, 65, 91, 33, 50, 104, 0, 87, 300, 0, 130, 65, 91,
            33, 50, 104, 0, 87, 51, 0, 130, 65, 0, 33, 50, 14, 0, 87, 330, 0, 34, 65, 91, 33, 50, 104, 0, 87, 300, 0, 130, 65, 91, 33, 50, 104, 0, 87, 300, 0, 130, 65, 91,
            33, 50, 104, 0, 87, 300, 0, 130, 65, 91, 33, 50, 104, 0, 87, 300, 0, 130, 65, 91,
            33, 50, 104, 0, 87, 51, 0, 130, 65, 0, 33, 50, 14, 0, 87, 330, 0, 34, 65, 91,33, 50, 104, 0, 87, 300, 0, 130, 65, 91, 33, 50, 104, 0, 87, 300, 0, 130, 65, 91,
            33, 50, 104, 0, 87, 300, 0, 130, 65, 91, 33, 50, 104, 0, 87, 300, 0, 130, 65, 91,
            33, 50, 104, 0, 87, 51, 0, 130, 65, 0, 33, 50, 14, 0, 87, 330, 0, 34, 65, 91,)
    var maxHistory: Int = exerciseHistory.max()
    LazyHorizontalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .height(147.dp),
        rows = GridCells.Fixed(7)
    ) {
        items(exerciseHistory.size) { prevDay ->
            DayBox(exerciseHistory[prevDay], maxHistory)
        }
    }
}

@Composable
fun DayBox(intenseAmount: Int, max: Int = 255) {
   val color = calculateColor(intenseAmount, max)

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
