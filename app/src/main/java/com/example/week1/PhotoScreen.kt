package com.example.week1

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PhotoScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Photo",
        modifier = modifier.padding(24 .dp)
    )
}
