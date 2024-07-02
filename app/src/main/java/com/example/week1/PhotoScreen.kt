package com.example.week1

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
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

val photos0 = listOf(
    Photo(R.drawable.reborn, R.string.photo_name_1, R.string.address_1),
    Photo(R.drawable.sejong, R.string.photo_name_2, R.string.address_1),
    Photo(R.drawable.areum, R.string.photo_name_3, R.string.address_1),
    Photo(R.drawable.plana, R.string.photo_name_4, R.string.address_1),
    Photo(R.drawable.thebody, R.string.photo_name_5, R.string.address_1),
    Photo(R.drawable.auditorium, R.string.photo_name_6, R.string.address_1),
    Photo(R.drawable.sportscomplex, R.string.photo_name_7, R.string.address_1)
)

val photos1 = listOf(
    Photo(R.drawable.reborn, R.string.photo_name_1, R.string.address_1),
    Photo(R.drawable.sejong, R.string.photo_name_2, R.string.address_1)
)

val photos2 = listOf(
    Photo(R.drawable.thebody, R.string.photo_name_5, R.string.address_1),
    Photo(R.drawable.auditorium, R.string.photo_name_6, R.string.address_1),
)

val photos = listOf(
    photos0,
    photos1,
    photos2

)
@Composable
fun WorkoutScreen(
    modifier: Modifier = Modifier
) {
    var selectedSports by rememberSaveable { mutableStateOf<Int?>(null) }
    val sports = listOf("Weight Training", "Running", "Climbing")

    if (selectedSports == null) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .background(MaterialTheme.colorScheme.background)
        ){
            Text(
                text = "운동",
                color = Color.White
            )
            LazyColumn {
                itemsIndexed(sports) {i, sport ->
                    SportsItem(
                        sports = sport,
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                    ) {
                        selectedSports = i
                    }
                }
            }
        }
    }
    else {
        PhotoScreen (selectedSports!!, modifier) {
            selectedSports = null
        }
    }
}

@Composable
fun SportsItem(
    sports: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(onClick = onClick) {
        Text(sports)
    }

}

@Composable
fun PhotoScreen(
    sports: Int,
    modifier: Modifier = Modifier,
    onHomeButton: () -> Unit
) {
    Column {
        Button(onClick = onHomeButton) {
            Text(text = "Home")
        }
        val scrollState = rememberLazyGridState()
        var selectedImageId by rememberSaveable { mutableStateOf<Int?>(null) }
        if (selectedImageId != null) {
            FullScreenImage(imageId = selectedImageId!!) {
                selectedImageId = null
            }
        } else {
            LazyVerticalGrid(
                state = scrollState,
                columns = GridCells.Fixed(3),
                modifier = modifier
            ) {
                items(photos[sports]) { photo ->
                    DrawPhoto(
                        photo = photo,
                        modifier = Modifier.clickable { selectedImageId = photo.imageResourceId }
                    )
                }
            }
        }
    }

}

@Composable
fun FullScreenImage(
    imageId: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
        Button(onClick = onClick) {
            Text(text = "Back")
        }
    }
}

@Composable
fun DrawPhoto(
    photo: Photo,
    modifier: Modifier = Modifier
) {
    Box {
        Image(
            painter = painterResource(id = photo.imageResourceId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .padding(1.dp)
                .aspectRatio(1f)
        )
        Text(
            text = stringResource(id = photo.nameId),
            fontSize = 14.sp,
            style = TextStyle(
                color = Color.White
            ),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 22.dp)
        )
        Text(
            text = stringResource(id = photo.addressId),
            fontSize = 7.sp,
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
        WorkoutScreen()
    }
}
