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

@Composable
fun CalendarScreen() {
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
        ExerciseTimeDialog(onDismiss = { showDialog = false })
    }

}

@Composable
fun ExerciseTimeDialog(onDismiss: () -> Unit) {
    var context = LocalContext.current
    val dialogView = LayoutInflater.from(context).inflate(R.layout.exercise_time_dialog, null)
    val alertDialog = AlertDialog.Builder(context)
        .setView(dialogView)
        .setOnDismissListener { onDismiss() }
        .create()

    dialogView.findViewById<android.widget.Button>(R.id.successButton).setOnClickListener {
        val calendarView = dialogView.findViewById<CalendarView>(R.id.calendarView)
        val editTime = dialogView.findViewById<EditText>(R.id.editTime)
        val selectedDate = calendarView.date
        val exerciseTime = editTime.text.toString()

        // Process the input values (e.g., save them or use them in the app)
        Toast.makeText(
            context,
            "성공적으로 저장되었습니다.",
            Toast.LENGTH_SHORT
        ).show()
        alertDialog.dismiss()
    }

    dialogView.findViewById<android.widget.Button>(R.id.declineButton).setOnClickListener {
        Toast.makeText(context, "저장을 취소하였습니다.", Toast.LENGTH_SHORT).show()
        alertDialog.dismiss()
    }

    alertDialog.show()
}

@Preview(showBackground = true)
@Composable
fun PreviewCalendarScreen() {
    CalendarScreen()
}