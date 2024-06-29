package com.example.week1

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week1.ui.theme.Week1Theme

data class Photo(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
)

val photos = listOf(
    Photo(R.drawable.boolguksa, R.string.photo_name_1),
    Photo(R.drawable.eiffel, R.string.photo_name_2),
    Photo(R.drawable.greatwall, R.string.photo_name_3),
    Photo(R.drawable.pyramid, R.string.photo_name_4),
    Photo(R.drawable.spinx, R.string.photo_name_5)
)

@Composable
fun PhotoScreen(
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(minSize = 128.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        items(photos) { photo ->
            DrawPhoto(id = photo.imageResourceId)
        }
    }
}

@Composable
fun DrawPhoto(
    id: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = id),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .padding(1.dp)
            .aspectRatio(1f / 1f)
            .background(Color.Yellow)
    )
}

@Preview(showBackground = true)
@Composable
fun PhotoPreview() {
    Week1Theme {
        PhotoScreen()
    }
}
