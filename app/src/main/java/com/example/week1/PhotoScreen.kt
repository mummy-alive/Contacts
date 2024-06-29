package com.example.week1

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week1.ui.theme.Week1Theme

data class Photo(
    @DrawableRes val imageResourceId: Int,
    @StringRes val nameId: Int,
    @StringRes val addressId: Int,
)

val photos = listOf(
    Photo(R.drawable.reborn, R.string.photo_name_1, R.string.address_1),
    Photo(R.drawable.sejong, R.string.photo_name_2, R.string.address_1),
    Photo(R.drawable.areum, R.string.photo_name_3, R.string.address_1),
    Photo(R.drawable.plana, R.string.photo_name_4, R.string.address_1),
    Photo(R.drawable.thebody, R.string.photo_name_5, R.string.address_1),
    Photo(R.drawable.auditorium, R.string.photo_name_6, R.string.address_1),
    Photo(R.drawable.sportscomplex, R.string.photo_name_7, R.string.address_1)
)

@Composable
fun PhotoScreen(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(3.dp),
    ) {
        items(photos) { info ->
            DrawPhoto(photo = info)
        }
    }
}

@Composable
fun DrawPhoto(
    photo: Photo,
    modifier: Modifier = Modifier
) {
    Box(
    ) {
        Image(
            painter = painterResource(id = photo.imageResourceId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .padding(1.dp)
                .aspectRatio(1f / 1f)
                .background(Color.Yellow)
        )
        Text(
            text = stringResource(id = photo.nameId),
            fontSize = 40.sp,
            style = TextStyle(
                color = Color.White
            ),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 40.dp)
        )
        Text(
            text = stringResource(id = photo.addressId),
            fontSize = 20.sp,
            style = TextStyle(
                color = Color.White
            ),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 15.dp)
        )

    }
}


@Preview(showBackground = true)
@Composable
fun PhotoPreview() {
    Week1Theme {
        PhotoScreen()
    }
}
