package com.example.week1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week1.ui.theme.Week1Theme
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen( modifier: Modifier = Modifier) {
    var shouldShowOnboarding by remember { mutableStateOf(true) }
    Surface(modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen{ shouldShowOnboarding = false }
        } else {
            TabScreen()
        }
    }
}

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onTimeout: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(2000L)
        onTimeout()
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(R.drawable.onboarding),
            contentDescription = null)
    }
}

@Composable
fun TabButton(
    modifier: Modifier = Modifier,
    activate: Boolean = false,
    imageVector: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(3.dp),
        contentAlignment = Alignment.Center
    ) {
        val tintColor = if (activate) {
            MaterialTheme.colorScheme.secondary
        }
        else {
            Color.Gray
        }
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(6.dp),
            modifier = modifier
                .alpha(0f)

        ) {}
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                tint = tintColor,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = text,
                fontSize = 12.sp,
                color = tintColor
            )
        }
    }
}

@Composable
fun TabScreen(
) {
    var tabIndex by rememberSaveable { mutableIntStateOf(0) }
    val tabs = listOf("친구야", "여기서", "운동하자")
    val tabIcon: Array<ImageVector> = arrayOf(
        Icons.Default.Person,
        Icons.Default.LocationOn,
        rememberDumbell()
    )
    Column(modifier = Modifier.fillMaxSize()){

        when (tabIndex) {
            0 -> ContactsScreen(modifier = Modifier
                .fillMaxSize()
                .weight(1f))
            1 -> WorkoutScreen(modifier = Modifier
                .fillMaxSize()
                .weight(1f)
            )
            2 -> CalendarScreen(modifier = Modifier
                .fillMaxSize()
                .weight(1f))
        }
        Row {
            tabs.forEachIndexed { i, title ->
                TabButton(
                    activate = (i == tabIndex),
                    imageVector = tabIcon[i],
                    text = title,
                    onClick = {
                        tabIndex = i
                    },
                    modifier = Modifier.weight(1f)
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

