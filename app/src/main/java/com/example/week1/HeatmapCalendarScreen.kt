package com.example.week1

import android.app.AlertDialog
import android.view.LayoutInflater
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Toast
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CalendarScreen() {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    var refreshCalendar by remember { mutableStateOf(false) }

    copyJsonFileFromAssetsIfNeeded(context, "exerciseHistory.json")

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