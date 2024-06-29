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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week1.ui.theme.Week1Theme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CalendarScreen(modifier: Modifier = Modifier) {
    CalendarView(modifier)
}

/*ToDo: 날짜 API 설정 완료하기 */
@Composable
fun CalendarView(modifier: Modifier) {
    /*ToDo: 날짜 API 설정 완료하기 */
    val dowArray: List<String> = listOf("일", "월", "화", "수", "목", "금", "토")
    val nowDate = "2023-10-24"
    val formatter = SimpleDateFormat("yyyy-mm-dd", Locale.KOREA)
    val date = Date()

    val yearState by remember { mutableStateOf(formatter.format(date).split('-').first()) }
    val monthState by remember { mutableStateOf(formatter.format(date).split('-')[1]) }
    val dayState by remember { mutableStateOf(formatter.format(date).split('-').last()) }

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(7)
    ) {
        items(7) { dow ->
            DowBox(dowArray[dow], modifier)

        }
        items(40) { day ->
            CalendarBox(day, modifier)
        }
    }
}

@Composable
fun DowBox(dow: String, modifier: Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .padding(vertical = 0.dp, horizontal = 0.dp)
            .border(0.dp, Color.LightGray, RoundedCornerShape(4.dp))
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
                    .padding(start = 2.dp, top = 2.dp),
                fontSize = 8.sp
            )
        }
    }
}

@Composable
fun CalendarBox(day: Int, modifier: Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .padding(vertical = 0.dp, horizontal = 0.dp)
            .border(0.dp, Color.LightGray, RoundedCornerShape(4.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
                .background(Color.Unspecified)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = day.toString(),
                    Modifier
                        .align(Alignment.Start),
                    //.padding(start = 2.dp, top = 2.dp),
                    fontSize = 8.sp
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = "오운완\uD83D\uDD25",
                    Modifier
                        .align(Alignment.CenterHorizontally),
                    //.padding(bottom = 4.dp),
                    fontSize = 6.sp
                )
            }
        }
    }
}