package com.example.week1

import android.content.Intent
import android.net.Uri
import android.view.ContextThemeWrapper
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.week1.data.Person
import com.example.week1.ui.theme.Week1Theme
import java.util.Calendar
import java.util.concurrent.TimeUnit


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
    "안유진" to "2024/06/24",
    "공진우" to "2024/07/03",
    "강해린" to "2024/06/30",
    "김가을" to "2019/03/14"
)

@Composable
fun ContactsScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val jsonString = readJsonFile(context, "peopleInfo.json")
    val people = parseJsonToPeople(jsonString)
    var selectedPerson by rememberSaveable { mutableStateOf<Person?>(null) }
    val scrollState = rememberLazyListState()

    if (selectedPerson == null) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)

        ){
            Text(
                text = "친구",
                color = Color.White
            )
            LazyColumn(
                state = scrollState
            ) {
                itemsIndexed(people) {_, person ->
                    ContactItem(
                        person = person,
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                    ) {
                        selectedPerson = person
                    }
                }
            }
        }
    }
    else {
        ShowCalendar (selectedPerson!!.recentExercise, modifier) { date ->
            nameToRecentExercise = nameToRecentExercise + (selectedPerson!!.name to date)
            selectedPerson = null
        }
    }
}

@Composable
fun ContactItem(
    person: Person,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    person.imageResourceId = nameToResourceIdMap[person.name] ?: R.drawable.default_image
    person.recentExercise = nameToRecentExercise[person.name] ?: "기록 없음"
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
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
            color = Color.White,
        )
        Text(
            text = personTel,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun RecentExercise(
    recentExercise: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    var varRecentExercise = recentExercise
    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        modifier = modifier
            .width(120.dp)
            .height(50.dp),
        onClick = onClick,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            var dayColor = Color.Gray
            if (recentExercise != "기록 없음") {
                dayColor = MaterialTheme.colorScheme.tertiary
                val today = Calendar.getInstance()
                val recent = stringToCalendar(recentExercise)
                val diffDays =
                    TimeUnit.MILLISECONDS.toDays(today.timeInMillis - recent.timeInMillis)
                varRecentExercise = if (diffDays == 0L) {
                    "오늘"
                } else {
                    "${diffDays}일 전"
                }
                if (diffDays > 10L) {
                    dayColor = Color.Red
                }
            }
            Text(
                text = varRecentExercise,
                color = dayColor
            )
        }
    }
}


@Composable
fun ShowCalendar(
    selectedDateString: String,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    var selectedDate = Calendar.getInstance()
    if (selectedDateString != "기록 없음") {
        selectedDate = stringToCalendar(selectedDateString)
    }
    val today = Calendar.getInstance()
    var date by remember { mutableStateOf("최근 함께 운동한 날이 언제인가요?") }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = date)
        AndroidView(
            factory = { context ->
                val themedContext = ContextThemeWrapper(context, R.style.CustomCalendar)
                android.widget.CalendarView(themedContext).apply {
                    dateTextAppearance = R.style.CustomDate
                    weekDayTextAppearance = R.style.CustomWeek
                    setDate(selectedDate.timeInMillis)
                }
            },
            update = { calendarView ->
                calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
                    date = "$year/${month + 1}/$dayOfMonth"
                    val dateDate = stringToCalendar(date)
                    if (
                        TimeUnit.MILLISECONDS.toDays(today.timeInMillis - dateDate.timeInMillis) >= 0
                        )
                    {
                        onClick(date)
                    }
                    else {
                        view.apply {
                            dateTextAppearance = R.style.CustomDate
                            weekDayTextAppearance = R.style.CustomWeek
                            setDate(selectedDate.timeInMillis)
                        }
                        date = "오늘 혹은 이전의 날을 선택해 주세요"
                    }
                }
            }
        )
        Button(
            onClick = {
                date = "기록 없음"
                onClick(date)
            },
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            /*TODO: Add Today button*/
            Text(text = "X")
        }
    }
}

fun stringToCalendar(date: String): Calendar {
    val parts = date.split("/")
    val set = Calendar.getInstance()
    set.set(parts[0].toInt(), parts[1].toInt() - 1, parts[2].toInt())
    return set
}

@Preview(showBackground = true)
@Composable
fun ContactsPreview() {
    Week1Theme {
        ContactsScreen()
    }
}