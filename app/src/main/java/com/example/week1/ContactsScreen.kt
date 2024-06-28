package com.example.week1

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ContactsScreen(
    modifier: Modifier = Modifier,
    peopleList: List<PERSON>
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)){

        for (person in peopleList)
            item {
                ContactInfo(person)
            }
    }
}

@Composable
fun ContactInfo(person: PERSON) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical =4.dp, horizontal = 8.dp)
    ) {
        Row(modifier=Modifier.fillMaxSize()
            .padding(24.dp)) {
            Text(text = person.name)
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = person.tel)
        }
    }
}