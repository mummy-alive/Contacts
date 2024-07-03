package com.example.week1

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp


@Composable
fun rememberDumbell(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "Dumbell",
            defaultWidth = 512.000000.dp,
            defaultHeight = 512.000000.dp,
            viewportWidth = 512f,
            viewportHeight = 512f
        ).apply {
            group(
                scaleX = 0.1f,
                scaleY = -0.1f,
                translationX = 0f,
                translationY = 512f,
                pivotX = 0f,
                pivotY = 0f,
            ) {
                path(
                    fill = SolidColor(Color(0xFF000000)),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(3320f, 4681f)
                    curveToRelative(-107f, -35f, -193f, -118f, -228f, -221f)
                    lineToRelative(-18f, -52f)
                    lineToRelative(-42f, 35f)
                    curveToRelative(-127f, 107f, -317f, 102f, -442f, -10f)
                    curveToRelative(-113f, -101f, -144f, -256f, -80f, -400f)
                    curveToRelative(16f, -37f, 81f, -108f, 278f, -306f)
                    lineToRelative(257f, -257f)
                    lineToRelative(-698f, -697f)
                    lineToRelative(-697f, -698f)
                    lineToRelative(-257f, 257f)
                    curveToRelative(-198f, 197f, -269f, 262f, -306f, 278f)
                    curveToRelative(-144f, 64f, -299f, 33f, -400f, -80f)
                    curveToRelative(-112f, -125f, -117f, -315f, -10f, -442f)
                    lineToRelative(35f, -42f)
                    lineToRelative(-52f, -18f)
                    curveToRelative(-85f, -29f, -153f, -88f, -198f, -173f)
                    curveToRelative(-23f, -44f, -27f, -61f, -27f, -145f)
                    curveToRelative(0f, -157f, -22f, -129f, 572f, -721f)
                    curveToRelative(493f, -492f, 521f, -518f, 578f, -539f)
                    curveToRelative(83f, -30f, 197f, -25f, 271f, 13f)
                    curveToRelative(71f, 36f, 148f, 123f, 171f, 193f)
                    lineToRelative(19f, 56f)
                    lineToRelative(42f, -35f)
                    curveToRelative(126f, -106f, 317f, -102f, 439f, 8f)
                    curveToRelative(115f, 103f, 147f, 258f, 83f, 402f)
                    curveToRelative(-16f, 37f, -81f, 108f, -278f, 306f)
                    lineToRelative(-257f, 257f)
                    lineToRelative(698f, 697f)
                    lineToRelative(697f, 698f)
                    lineToRelative(257f, -257f)
                    curveToRelative(198f, -197f, 269f, -262f, 306f, -278f)
                    curveToRelative(144f, -64f, 299f, -32f, 402f, 83f)
                    curveToRelative(110f, 122f, 114f, 313f, 8f, 439f)
                    lineToRelative(-35f, 42f)
                    lineToRelative(56f, 19f)
                    curveToRelative(70f, 23f, 157f, 100f, 193f, 171f)
                    curveToRelative(38f, 74f, 43f, 188f, 13f, 271f)
                    curveToRelative(-21f, 57f, -47f, 85f, -539f, 578f)
                    curveToRelative(-582f, 584f, -565f, 570f, -706f, 574f)
                    curveToRelative(-44f, 1f, -91f, -1f, -105f, -6f)
                    close()
                }
            }
        }.build()
    }
}

