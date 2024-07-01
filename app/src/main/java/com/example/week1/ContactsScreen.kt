@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.week1

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.week1.data.Person
import com.example.week1.ui.theme.Week1Theme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.util.Calendar
import java.util.concurrent.TimeUnit

@Composable
fun ContactsScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val jsonString = readJsonFile(context, "peopleInfo.json")
    val people = parseJsonToPeople(jsonString)
    var selectedPerson by rememberSaveable { mutableStateOf<Person?>(null) }

    if (selectedPerson == null) {
        LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
            itemsIndexed(people) {_, person ->
                ContactItem(
                    person = person,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                ) {
                    selectedPerson = person
                    //nameToRecentExercise = nameToRecentExercise.plus(Pair(it.name, calendarToString(Calendar.getInstance())))
                }
            }
        }
    }
    else {
        ShowCalendar(selectedPerson!!) {
            selectedPerson = null
        }
    }

}

val nameToResourceIdMap = mapOf(
    "안유진" to R.drawable.ayj,
    "장원영" to R.drawable.jwy,
    "김가을" to R.drawable.kge,
    "나오이 레이" to R.drawable.rei,
    "김지원" to R.drawable.kjw,
    "이현서" to R.drawable.lhs,
    "강해린" to R.drawable.khr,
    "김민지" to R.drawable.kmj,
    "모지혜" to R.drawable.mzh,
    "팜하니" to R.drawable.phn,
    "이혜인" to R.drawable.lhi
)

var nameToRecentExercise = mapOf(
    "안유진" to "2024/06/01"
)

@Composable
fun ContactItem(
    person: Person,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
//    Surface(onClick = onClick) {
//        Text(text = person.name)
//    }
    val context = LocalContext.current
    person.imageResourceId = nameToResourceIdMap[person.name] ?: R.drawable.default_image
    person.recentExercise = nameToRecentExercise[person.name] ?: "기록 없음"
    Card(
        modifier = modifier,
        onClick = {
            val u = Uri.parse("tel:${person.tel}")
            val i = Intent(Intent.ACTION_DIAL, u)
            context.startActivity(i)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_small)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //PersonIcon(person.imageResourceId)
            PersonInformation(person.name, person.tel)
            Spacer(Modifier.weight(1f))
            RecentExercise(person.recentExercise, modifier, onClick)
        }
    }
}

@Composable
fun PersonIcon(
    @DrawableRes personIcon: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(dimensionResource(R.dimen.image_size))
            .padding(dimensionResource(R.dimen.padding_small))
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop,
        painter = painterResource(personIcon),
        contentDescription = null
    )
}

@Composable
fun PersonInformation(
    personName: String,
    personTel: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = personName,
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = personTel
        )
    }
}

@Composable
fun RecentExercise(
    recentExercise: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        color = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
    ) {
        if (recentExercise == "기록 없음") {
            Text(text = "기록 없음")
        } else {
            val today = Calendar.getInstance()
            val recent = stringToCalendar(recentExercise)

            val diffDays = TimeUnit.MILLISECONDS.toDays(today.timeInMillis - recent.timeInMillis)
            Text(text = "${diffDays}일 전")
        }
    }
}


@Composable
fun ShowCalendar(
    person: Person,
    onClick: () -> Unit
) {
    var selectedDate = Calendar.getInstance()
    val year = selectedDate.get(Calendar.YEAR)
    val month = selectedDate.get(Calendar.MONTH)
    val day = selectedDate.get(Calendar.DAY_OF_MONTH)
    var date by remember { mutableStateOf("$year/${month + 1}/$day") }
    Column {
        AndroidView(
            factory = { context ->
                android.widget.CalendarView(context).apply { setDate(selectedDate.timeInMillis) }
            },
            update = { calendarView ->
                calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
                    date = "$year/${month + 1}/$dayOfMonth"
                    onClick()
                }
            }
        )
        Text(text = date)
    }
}

fun readJsonFile(context: Context, fileName: String): String {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            ""
        }
    }

fun parseJsonToPeople(jsonString: String): List<Person> {
    val gson = Gson()
    val listType = object : TypeToken<List<Person>>() {}.type
    val people: List<Person> = gson.fromJson(jsonString, listType)
    return people.sortedBy { it.name }
}

fun stringToCalendar(date: String): Calendar {
    val parts = date.split("/")
    val set = Calendar.getInstance()
    set.set(parts[0].toInt(), parts[1].toInt() - 1, parts[2].toInt())
    return set
}

fun calendarToString(date: Calendar): String {
    val year = date.get(Calendar.YEAR)
    val month = date.get(Calendar.MONTH) + 1
    val day = date.get(Calendar.DAY_OF_MONTH)
    return "$year/${month+1}/$day"
}


@Preview(showBackground = true)
@Composable
fun ContactsPreview() {
    Week1Theme {
        ContactsScreen()
    }
}
