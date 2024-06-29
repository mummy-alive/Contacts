package com.example.week1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.week1.ui.theme.Week1Theme
import org.json.*
import java.io.InputStream

data class PERSON(
    var name: String,
    var tel: String
)

class MainActivity : ComponentActivity() {

    private lateinit var peopleList: List<PERSON>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        peopleList = readPeopleFromJson()
        setContent {
            Week1Theme {
                TabScreen(modifier = Modifier.fillMaxSize(), peopleList = peopleList)
            }
        }
    }

    private fun readPeopleFromJson(): List<PERSON> {
        val json: String
        val inputStream: InputStream = assets.open("peopleInfo.json")
        json = inputStream.bufferedReader().use { it.readText() }
        val jsonObject = JSONObject(json)
        val jsonArray = jsonObject.getJSONArray("member")

        val peopleList = mutableListOf<PERSON>()
        for (i in 0 until jsonArray.length()) {
            val item = jsonArray.getJSONObject(i)
            val name = item.getString("name")
            val tel = item.getString("tel")
            peopleList.add(PERSON(name, tel))
        }
        return peopleList
    }
}

@Composable
fun TabScreen(modifier: Modifier = Modifier,
              peopleList: List<PERSON>) {
    var tabIndex by rememberSaveable { mutableIntStateOf(0) }
    val tabs = listOf("Contacts", "Photo", "Third")

    Column(modifier = modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> ContactsScreen(peopleList = peopleList)
            1 -> PhotoScreen()
            2 -> ThirdScreen()
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 200)
@Composable
fun ContactPreview(){
    Week1Theme {
        ContactsScreen( peopleList = listOf(
            PERSON(name = "John Doe", tel = "010-8188-2222"),
            PERSON(name = "Amy Pearson", tel = "010-9999-4444")
        ))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Week1Theme {
        TabScreen(peopleList = listOf(
            PERSON(name = "John Doe", tel = "010-8188-2222"),
            PERSON(name = "Amy Pearson", tel = "010-9999-4444")
        ))
    }
}