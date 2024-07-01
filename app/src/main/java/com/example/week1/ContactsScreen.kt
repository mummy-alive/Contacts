package com.example.week1

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week1.data.Person
import com.example.week1.data.people
import com.example.week1.ui.theme.Week1Theme

@Composable
fun ContactsScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(people) {
            ContactItem(
                    person = it,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
        }
    }
}

@Composable
fun ContactItem(
    person: Person,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
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
    @StringRes personName: Int,
    @StringRes personTel: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(personName),
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = stringResource(personTel)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContactPreview() {
    Week1Theme {
        ContactsScreen()
    }
}