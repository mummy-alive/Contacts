package com.example.week1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week1.data.Photo
import com.example.week1.data.photos
import com.example.week1.ui.theme.Week1Theme

val sports = listOf("모두", "헬스", "클라이밍", "러닝", "수영", "복싱", "필라테스")

@Composable
fun WorkoutScreen(
    modifier: Modifier = Modifier
) {
    var selectedSports by rememberSaveable { mutableIntStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyRow {
            itemsIndexed(sports) {i, sport ->
                SportsItem(
                    sports = sport
                ) {
                    selectedSports = i
                }
            }
        }
        PhotoScreen (selectedSports, modifier)
    }
}

@Composable
fun SportsItem(
    sports: String,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .height(40.dp)
            .padding(3.dp),
        colors = ButtonColors(
            MaterialTheme.colorScheme.secondary,
            Color.Black,
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.secondary
            ),
        onClick = onClick) {
        Text(sports)
    }

}

@Composable
fun PhotoScreen(
    sports: Int,
    modifier: Modifier = Modifier
) {
    Column {
        val scrollState = rememberLazyGridState()
        var selectedImageId by rememberSaveable { mutableStateOf<Int?>(null) }
        if (selectedImageId != null) {
            FullScreenImage(imageId = selectedImageId!!) {
                selectedImageId = null
            }
        } else {
            LazyVerticalGrid(
                state = scrollState,
                columns = GridCells.Fixed(2),
                modifier = modifier
            ) {
                items(photos[sports]) { photo ->
                    DrawPhoto(
                        photo = photo,
                        modifier = Modifier
                            .clickable { selectedImageId = photo.imageResourceId }
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
        Card(
            colors = CardColors(
                MaterialTheme.colorScheme.secondary,
                MaterialTheme.colorScheme.secondary,
                MaterialTheme.colorScheme.secondary,
                MaterialTheme.colorScheme.secondary,
                ),
            onClick = onClick
        ) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        }
    }
}

@Composable
fun DrawPhoto(
    photo: Photo,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
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
            fontSize = 11.sp,
            style = TextStyle(
                color = Color.White
            ),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 22.dp)
                .drawBehind {
                    val borderSize = 4.dp.toPx()
                    drawRect(
                        color = Color.Black,
                        size = size.copy(
                            width = size.width + borderSize,
                            height = size.height + borderSize
                        ),
                        topLeft = Offset(-borderSize / 2, -borderSize / 2)
                    )
                },
        )
        Text(
            text = stringResource(id = photo.addressId),
            fontSize = 9.sp,
            style = TextStyle(
                color = Color.White
            ),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 7.dp)
                .drawBehind {
                    val borderSize = 4.dp.toPx()
                    drawRect(
                        color = Color.Black,
                        size = size.copy(
                            width = size.width + borderSize,
                            height = size.height + borderSize
                        ),
                        topLeft = Offset(-borderSize / 2, -borderSize / 2)
                    )
                },
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
