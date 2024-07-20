import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gipsdkdemo.shared.generated.resources.Res
import gipsdkdemo.shared.generated.resources.compose_multiplatform
import io.gupshup.gipsdkdemo.Greeting
import io.gupshup.gipsdkdemo.platform.GipChatHelper
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

@Composable
@Preview
fun App() {
    KoinContext {
        MaterialTheme {
            var showContent by remember { mutableStateOf(false) }
            val state = rememberScrollState()
            
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                ElevatedButton(
                    modifier = Modifier.padding(20.dp),
                    onClick = { showContent = !showContent }
                ) {
                    Text("Click me!")
                }
                AnimatedVisibility(!showContent) {
                    DefaultKMMView()
                }
                AnimatedVisibility(showContent) {

                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        MainScreen(modifier = Modifier)
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