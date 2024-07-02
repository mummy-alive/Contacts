package com.example.week1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
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


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    TabScreen()
                }
            }
        }
    }
}

@Composable
fun TabScreen(
) {
    var tabIndex by rememberSaveable { mutableIntStateOf(0) }
    val tabs = listOf("Contacts", "Photo", "Calendar")

    Column{
        when (tabIndex) {
            0 -> ContactsScreen(modifier = Modifier.fillMaxSize().weight(1f))
            1 -> PhotoScreen(modifier = Modifier.fillMaxSize().weight(1f))
            2 -> CalendarScreen()
        }
        TabRow(
            selectedTabIndex = tabIndex,
            modifier = Modifier.fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TabPreview() {
    Week1Theme {
        TabScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarPreview() {
    Week1Theme {
        CalendarScreen()
    }
}
