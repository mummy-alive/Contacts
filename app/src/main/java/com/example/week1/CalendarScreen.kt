package com.example.week1

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CalendarScreen(modifier: Modifier = Modifier) {
    CalendarView(modifier)
}

@Composable
fun CalendarView(modifier: Modifier){
    LazyVerticalGrid(
        modifier=Modifier.fillMaxSize(),
        columns =GridCells.Adaptive(minSize=68.dp)
    ) {
        items(40) { day ->
            CalendarBox(day, modifier)
        }
    }
}

@Composable
fun CalendarBox(day: Int, modifier: Modifier){
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.padding(vertical=2.dp, horizontal=2.dp)
    ) {
        Column(modifier=Modifier.fillMaxSize()
            .padding(vertical = 4.dp)){
            Text(text = day.toString())
            Spacer(modifier = Modifier.size(30.dp))
            Text(text = "오운완\uD83D\uDD25")
        }

    }

}