package com.example.week1

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week1.ui.theme.Week1Theme
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

/* WARNING: This file is to be deprecated. Now migrated to HeatmapCalendarScreen.kt
   주의: 이 파일은 곧 삭제됩니다. 해당 화면은 HeatmapCalendarScreen.kt로 마이그레이션되었습니다
 */
/*
@Composable
fun CalendarScreen(modifier: Modifier = Modifier) {

    CalendarView(modifier)
}

@Composable
fun CalendarView(
    modifier: Modifier,
) {
    val currentDate: LocalDate = LocalDate.now()
    val dowArray: List<String> = listOf("일", "월", "화", "수", "목", "금", "토")

    var yearState by remember { mutableIntStateOf(currentDate.year) }
    var monthState by remember { mutableIntStateOf(currentDate.monthValue) }
    var dayState by remember { mutableIntStateOf(currentDate.dayOfMonth) }

    fun monthIncrement() {
        if (monthState == 12) {
            monthState = 1
            yearState += 1
        } else
            monthState += 1
    }

    fun monthDecrement() {
        if (monthState == 1) {
            monthState = 12
            yearState -= 1
        } else
            monthState -= 1
    }

    Box(
        modifier = Modifier
            .background(Color.Unspecified)
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { monthDecrement() }) {
                Text("<<")
            }
            Text( //This is where we show month and year
                text = "${yearState}년 ${monthState}월",
                modifier = modifier
                    .padding(top = 16.dp),
                textAlign = TextAlign.Center
            )
            IconButton(onClick = { monthIncrement() }) {
                Text(">>")
            }
        }
    }
    Box(
        modifier = Modifier
            .size(700.dp)
            .background(Color.Unspecified)
            .padding(4.dp)
    ) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(), columns = GridCells.Fixed(7)
        ) {
            items(7) { dow ->
                DowBox(dowArray[dow])
            }
            items(40) { day ->
                var flg:Boolean = false
                if (day < 4) flg = true
                CalendarBox(day,flg)
            }
        }
    }
}

@Composable
fun DowBox(dow: String) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .padding(vertical = 0.dp, horizontal = 0.dp)
            .border(0.dp, Color.LightGray, RoundedCornerShape(4.dp))
            .aspectRatio(1.7f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
                .background(Color.Unspecified)
        ) {
            Text(
                text = dow,
                Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 1.dp, top = 1.dp),
                fontSize = 8.sp
            )
        }
    }
}

@Composable
fun CalendarBox(day: Int, isBlank: Boolean) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .padding(vertical = 0.dp, horizontal = 0.dp)
            .border(0.dp, Color.LightGray, RoundedCornerShape(4.dp))
            .aspectRatio(1.2f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
                .background(Color.Unspecified)
        ) {
            if (!isBlank) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = day.toString(), Modifier.align(Alignment.Start),
                        //.padding(start = 2.dp, top = 2.dp),
                        fontSize = 8.sp
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = "오운완\uD83D\uDD25", Modifier.align(Alignment.CenterHorizontally),
                        //.padding(bottom = 4.dp),
                        fontSize = 6.sp
                    )
                }
            }
        }
    }
}*/