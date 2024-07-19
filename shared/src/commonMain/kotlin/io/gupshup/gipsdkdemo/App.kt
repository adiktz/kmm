import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import gipsdkdemo.shared.generated.resources.Res
import gipsdkdemo.shared.generated.resources.compose_multiplatform
import io.gupshup.gipsdkdemo.Greeting
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        val state = rememberScrollState()
        
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
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