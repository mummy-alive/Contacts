package com.example.week1

import android.app.AlertDialog
import android.view.LayoutInflater
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.week1.databinding.ExerciseTimeDialogBinding
import kotlinx.coroutines.selects.select
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

@Composable
fun CalendarScreen() {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    Column {
        HeatmapCalendar()
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("운동 시간 기록하기")
        }
    }
    if (showDialog) {
        ExerciseTimeDialog(
            onDismiss = { showDialog = false },
            onSave = {date, exerciseTime -> StoreExerciseOnJson(context, date, exerciseTime)})
    }
}

@Composable
fun ExerciseTimeDialog(onDismiss: () -> Unit, onSave:(Int, Int) -> Unit) {
    var context = LocalContext.current
    val dialogView = LayoutInflater.from(context).inflate(R.layout.exercise_time_dialog, null)
    val alertDialog = AlertDialog.Builder(context)
        .setView(dialogView)
        .setOnDismissListener { onDismiss() }
        .create()
    dialogView.findViewById<android.widget.Button>(R.id.successButton).setOnClickListener {
        val calendarView = dialogView.findViewById<CalendarView>(R.id.calendarView)
        val editTime = dialogView.findViewById<EditText>(R.id.editTime)
        val selectedDate = calendarView.date.toInt()
        val exerciseTime = editTime.text.toString().toIntOrNull()
        if (exerciseTime != null) {
            onSave(selectedDate, exerciseTime)
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

fun StoreExerciseOnJson(context: Context, date: Int, exerciseTime: Int){
    val year:Int = date/10000
    val month = (date%10000)/100
    val day = date%100
    val jsonString = readJsonFile(context, "exerciseHistory.json")

    val outputString = modifyDateHistoryValue(jsonString, year, month, day, exerciseTime)
}

@Preview(showBackground = true)
@Composable
fun PreviewCalendarScreen() {
    CalendarScreen()
}