package io.gupshup.gipsdkdemo

import MainScreen
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gipsdkdemo.shared.generated.resources.Res
import gipsdkdemo.shared.generated.resources.compose_multiplatform
import io.gupshup.gipsdkdemo.ui.theme.SharedTheme
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
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
                var isGipInitialized by remember { mutableStateOf(false) }
                val snackbarHostState = remember { SnackbarHostState() }
                val channel = remember { Channel<String>(Channel.CONFLATED) }
                LaunchedEffect(channel) {
                    channel.receiveAsFlow().collect { message ->
                        val result = snackbarHostState.showSnackbar(
                            message = message
                        )
                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                /* action has been performed */
                            }

                            SnackbarResult.Dismissed -> {
                                /* dismissed, no action needed */
                            }
                        }
                    }
                }

                Scaffold(
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    contentWindowInsets = ScaffoldDefaults.contentWindowInsets
                ) { innerPadding ->
                    Column(
                        Modifier.fillMaxWidth()
                            .padding(innerPadding)
                            .systemBarsPadding(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        MainScreen(
                            modifier = Modifier,
                            channel = channel,
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