package io.gupshup.gipsdkdemo

import MainScreen
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import gipsdkdemo.shared.generated.resources.Res
import gipsdkdemo.shared.generated.resources.compose_multiplatform
import io.gupshup.gipsdkdemo.ui.theme.SharedTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    KoinContext {
        SharedTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                val showContent by remember { mutableStateOf(true) }

                var isGipInitialized by remember { mutableStateOf(false) }

                val magicTextAlpha: Float by animateFloatAsState(if (showContent) 1f else 0f)
                val hideMagicTextAlpha: Float by animateFloatAsState(if (showContent) 0f else 1f)

                Column(
                    Modifier.fillMaxWidth()
                        .systemBarsPadding(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    /*ElevatedButton(
                        modifier = Modifier.padding(30.dp),
                        onClick = { showContent = !showContent }
                    ) {
                        if (showContent)
                            Text(
                                "Hide The Awesome Content!".uppercase(),
                                modifier = Modifier.alpha(magicTextAlpha)
                            )
                        else
                            Text(
                                "Show the awesome content!".uppercase(),
                                modifier = Modifier.alpha(hideMagicTextAlpha)
                            )
                    }*/
                    /*AnimatedVisibility(!showContent) {
                        DefaultKMMView()
                    }*/
                    AnimatedVisibility(showContent) {

                        Column(
                            Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            MainScreen(
                                modifier = Modifier,
                                isGipInitialized = isGipInitialized,
                                setInitialized = {
                                    isGipInitialized = it
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DefaultKMMView() {
    val greeting = remember { Greeting().greet() }
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "First KMM App",
            modifier = Modifier
                .padding(top = 20.dp)
        )
        Image(
            painterResource(Res.drawable.compose_multiplatform),
            null
        )
        Text("Compose: $greeting")
    }
}