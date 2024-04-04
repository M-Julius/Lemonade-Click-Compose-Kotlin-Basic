package com.juldev.lemonadeclick

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juldev.lemonadeclick.ui.theme.LemonadeClickTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeClickTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonApp() {
    var currentStep by remember { mutableIntStateOf(1) }

    var squezeeCount by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF9E44C),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }

            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background,
        ) {
            when (currentStep) {
                1 -> {
                    Lemonade(
                        title = R.string.tap_lemon,
                        image = R.drawable.lemon_tree
                    ) {
                        currentStep = 2
                        squezeeCount = (2..4).random()
                    }
                }
                2 -> {
                    Lemonade(
                        title = R.string.keep_tap_lemon,
                        image = R.drawable.lemon_squeeze) {
                        squezeeCount --
                        if(squezeeCount == 0) {
                            currentStep = 3
                        }
                    }
                }
                3 -> {
                    Lemonade(
                        title = R.string.drink_lemon,
                        image = R.drawable.lemon_drink) {
                        currentStep = 4
                    }
                }
                4 -> {
                    Lemonade(
                        title = R.string.empty_lemon,
                        image = R.drawable.lemon_restart) {
                        currentStep = 1
                    }
                }
            }
        }
    }
}

@Composable
fun Lemonade(title: Int, image: Int, onClick: () -> Unit) {
    Box {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC3ECD2)),
                onClick = { onClick() }) {
                Image(
                    painter = painterResource(image),
                    contentDescription = stringResource(title),
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .wrapContentSize()
                        .size(250.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .padding(16.dp)

                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = stringResource(title))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeClickTheme {
        LemonApp()
    }
}