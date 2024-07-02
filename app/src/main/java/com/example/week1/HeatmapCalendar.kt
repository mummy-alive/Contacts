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
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.ColorUtils
import com.example.week1.data.DateHistory
import com.example.week1.data.History
import kotlin.math.max

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
fun DayBox(history: History, max: Int = 255) {
    val color = calculateColor(max(max, history.exercise), max)

    Box(
        modifier = Modifier
            .padding(1.dp)
            .border(0.5.dp, Color.Transparent, RoundedCornerShape(3.dp))
            .size(20.dp)
            .aspectRatio(1.0f)
            .background(color)
    )
}

private fun calculateColor(intenseAmount: Int, max: Int): Color {
    val baseColor = Color(0xFF252D3A) // Starting color (gray)
    //val targetColor = Color(0xFF41C3E4)
    val targetColorInt = 0xFF41C3E4.toInt() // Convert to Int for ColorUtils
    val darkness = if (max > 0) intenseAmount.toFloat() / max else 0f

    return if (intenseAmount == 0) {
        baseColor
    } else {
        // Convert targetColor to HSL
        val hsl = FloatArray(3)
        ColorUtils.colorToHSL(targetColorInt, hsl)

        // Adjust saturation based on darkness (0 to 1)
        hsl[1] = hsl[1] * darkness

        // Convert back to RGB and return as Color
        val adjustedColorInt = ColorUtils.HSLToColor(hsl)
        Color(adjustedColorInt)
    }
}
