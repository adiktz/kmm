import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.gupshup.gipsdkdemo.getPlatform
import io.gupshup.gipsdkdemo.platform.GipChatHelper
import org.koin.compose.koinInject

@Composable
fun MainScreen(
    modifier: Modifier,
    isGipInitialized: Boolean,
    setInitialized: (Boolean) -> Unit,
    gipChat: GipChatHelper = koinInject()
) {
    var appId by remember { mutableStateOf("c5453fbf-95e6-4330-9e14-28a85d3ea6a5") }
    var userName by remember { mutableStateOf("Gaurav") }
    var userId by remember { mutableStateOf("Test-User-Id-1234") }
//    var initialized by remember { mutableStateOf(false) }
//    val gipChat = remember { getGipChat() }
    val title = remember { getPlatform().title }
    Column(
        modifier = modifier
            .fillMaxSize()
            //   .background(Color.Cyan)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.weight(0.5f))
        TextField(
            value = appId,
            onValueChange = { appId = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            singleLine = true,
            label = { Text("App ID") }
        )
        TextField(
            value = userName,
            onValueChange = { userName = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            singleLine = true,
            label = { Text("User Name") }
        )
        TextField(
            value = userId,
            onValueChange = { userId = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            singleLine = true,
            label = { Text("User ID") }
        )

        ElevatedButton(onClick = {
            gipChat.setAppId(appId)
            gipChat.setUserName(userName)
            gipChat.setUserId(userId)
            gipChat.initialize { value ->
                setInitialized(value)
            }
        }) {
            if (isGipInitialized)
                Text(text = "Re-Initialize")
            else Text(text = "Initialize")
        }

        Spacer(modifier = Modifier.weight(1f))
        ElevatedButton(
            onClick = { gipChat.show() },
            enabled = isGipInitialized
        ) {
            Text(text = "Start Chat")
        }
        
        Spacer(modifier = Modifier.weight(0.3f))
    }
}