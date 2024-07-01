package com.example.week1

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week1.data.Person
import com.example.week1.ui.theme.Week1Theme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

@Composable
fun ContactsScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val jsonString = readJsonFile(context, "peopleInfo.json")
    val people = parseJsonToPeople(jsonString)
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(people) {
            ContactItem(
                    person = it,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
            )
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

@Composable
fun ContactItem(
    person: Person,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    person.imageResourceId = nameToResourceIdMap[person.name] ?: R.drawable.default_image
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
                .padding(dimensionResource(R.dimen.padding_small))
        ) {
            PersonIcon(person.imageResourceId)
            PersonInformation(person.name, person.tel)
            Spacer(Modifier.weight(1f))
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
    return gson.fromJson(jsonString, listType)
}

@Preview(showBackground = true)
@Composable
fun ContactPreview() {
    Week1Theme {
        ContactsScreen()
    }
}