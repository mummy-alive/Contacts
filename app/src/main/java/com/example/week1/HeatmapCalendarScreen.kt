package com.example.week1

import android.app.AlertDialog
import android.view.LayoutInflater
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week1.data.DateHistory
import java.time.LocalDate
import kotlin.random.Random

@Composable
fun CalendarScreen() {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    var refreshCalendar by remember { mutableStateOf(false) }
    val quotations = arrayOf(
        "\"우리가 늙어서 운동을 그만두는 것이 아니라, 우리가 운동을 그만두기 때문에 늙는 것이다\" - 케너스 쿠퍼",
        "\"운동은 우리가 가지고 있는 최고의 약이다.\" - 히포크라테스",
        "\"운동은 건강한 생활의 핵심이다.\" - 로버트 케네디",
        "\"운동은 우리가 몸과 마음을 건강하게 유지하고, 더욱 삶에 대한 긍정적인 태도를 가지게 한다.\" - 존 F. 케네디",
        "\"운동은 우리가 살아가는 방식을 변화시키고, 보다 더 건강하고 자신감 있는 삶을 살게 한다.\" - 미셸 오바마",
        "\"더 나은 결과를 얻기 위해서는 더 높은 기준을 세워야 한다.\" - 로버트 존슨",
        "\"약자는 변명을 잊고, 강자는 결과를 잊는다.\" - 프랭크 러벨",
        "\"피할수 없으면 즐겨라.\" - 로버트 엘리엇"
    )
    copyJsonFileFromAssetsIfNeeded(context, "exerciseHistory.json")
    val gradientColors = listOf(Cyan, MaterialTheme.colorScheme.primary, Color(0xFF7F00FF))
    val jsonString = readJsonFileFromInternalStorage(context, "exerciseHistory.json")
    val exerciseHistory: DateHistory = parseJsonToDateHistory(jsonString)
    val currentDate: LocalDate = LocalDate.now()
    var yearState by remember { mutableIntStateOf(currentDate.year) }
    var monthState by remember { mutableIntStateOf(currentDate.monthValue) }
    var dayState by remember { mutableIntStateOf(currentDate.dayOfMonth) }
    var dowState by remember { mutableStateOf(currentDate.dayOfWeek) }
    var maxSteadyDay by remember { mutableIntStateOf(MaxSteadyDay(exerciseHistory.history)) }
    var monthlyExercise by remember {
        mutableIntStateOf(
            ThisMonthExercise(
                exerciseHistory.history,
                yearState,
                monthState
            )
        )
    }
    var weeklyExercise by remember {
        mutableIntStateOf(
            ThisWeekExercise(
                exerciseHistory.history,
                yearState,
                monthState,
                dayState,
                dowState.value
            )
        )
    }
    Column(
        modifier = Modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,

        ) {
        HeatmapCalendar()
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = ("이번주 누적 운동 시간은 ${weeklyExercise / 60}시간 ${weeklyExercise % 60}분 이에요.\n" +
                    "이번달 누적 운동 시간은 ${monthlyExercise / 60}시간 ${monthlyExercise % 60}분 이에요."),
            color = MaterialTheme.colorScheme.secondary,
        )
        Text(
            text = ("최고 연속 운동일수: ${maxSteadyDay}일"),
            color = MaterialTheme.colorScheme.secondary,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = quotations[(0..quotations.size - 1).random()],
            fontStyle = FontStyle.Italic,

            style = TextStyle(
                brush = Brush.linearGradient(colors = gradientColors)
            ),
            color = MaterialTheme.colorScheme.primary,
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { showDialog = true },
            modifier = Modifier.padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("운동 시간 기록하기", color = MaterialTheme.colorScheme.background)
        }
    }
    if (showDialog) {
        ExerciseTimeDialog(
            onDismiss = { showDialog = false },
            onSave = { year, month, day, exerciseTime ->
                StoreExerciseOnJson(
                    context,
                    year,
                    month,
                    day,
                    exerciseTime
                )
                refreshCalendar != refreshCalendar
            })
    }
}

@Composable
fun ExerciseTimeDialog(onDismiss: () -> Unit, onSave: (Int, Int, Int, Int) -> Unit) {
    var context = LocalContext.current
    val dialogView = LayoutInflater.from(context).inflate(R.layout.exercise_time_dialog, null)
    val alertDialog = AlertDialog.Builder(context)
        .setView(dialogView)
        .setOnDismissListener { onDismiss() }
        .create()

    var selectedDate by remember { mutableStateOf(java.util.Calendar.getInstance().timeInMillis) }
    val calendarView = dialogView.findViewById<CalendarView>(R.id.calendarView)
    calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
        val calendar = java.util.Calendar.getInstance().apply {
            set(year, month, dayOfMonth)
        }
        selectedDate = calendar.timeInMillis
    }

    dialogView.findViewById<android.widget.Button>(R.id.successButton).setOnClickListener {
        val editTime = dialogView.findViewById<EditText>(R.id.editTime)
        val exerciseTime = editTime.text.toString().toIntOrNull()
        if (exerciseTime != null) {
            val calendar = java.util.Calendar.getInstance().apply {
                timeInMillis = selectedDate
            }
            val year = calendar.get(java.util.Calendar.YEAR)
            val month = calendar.get(java.util.Calendar.MONTH) + 1 // Months are 0-based
            val day = calendar.get(java.util.Calendar.DAY_OF_MONTH)

            onSave(year, month, day, exerciseTime)
            Toast.makeText(
                context,
                "성공적으로 저장되었습니다.",
                Toast.LENGTH_SHORT
            ).show()
        }
        // Process the input values (e.g., save them or use them in the app)
        alertDialog.dismiss()
    }

    dialogView.findViewById<android.widget.Button>(R.id.declineButton).setOnClickListener {
        Toast.makeText(context, "저장을 취소하였습니다.", Toast.LENGTH_SHORT).show()
        alertDialog.dismiss()
    }


    alertDialog.show()
}

fun StoreExerciseOnJson(
    context: Context,
    year: Int, month: Int, day: Int, exerciseTime: Int
) {
    val fileName = "exerciseHistory.json"
    val jsonString = readJsonFileFromInternalStorage(context, fileName)

    val outputString = modifyDateHistoryValue(jsonString, year, month, day, exerciseTime)
    writeJsonFileToInternalStorage(context, fileName, outputString)
}

@Preview(showBackground = true)
@Composable
fun PreviewCalendarScreen() {
    CalendarScreen()
}