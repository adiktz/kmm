import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(modifier: Modifier) {
    var appId by remember { mutableStateOf("c5453fbf-95e6-4330-9e14-28a85d3ea6a5") }
    var userName by remember { mutableStateOf("Gaurav") }
    var userId by remember { mutableStateOf("Test-User-Id-1234") }
    val initialized by remember { mutableStateOf(false) }
//    val gipChat = remember { getGipChat() }
    
    Column(
        modifier = modifier
            .fillMaxSize()
            //   .background(Color.Cyan)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "GipSDK Demo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption
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

        Button(onClick = {
//            gipChat.initialize()
//            GipChat.setAppId(appId)
//            GipChat.setUserName(userName)
//            GipChat.setUserId(userId)
//            GipChat.initialize(context) { value ->
//                initialized = value
//            }
        }) {
            if (initialized)
                Text(text = "Re-Initialize")
            else Text(text = "Initialize")
        }

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { /*GipChat.show()*/ },
            enabled = initialized
        ) {
            Text(text = "Start Chat")
        }
        
        Spacer(modifier = Modifier.weight(0.3f))
    }
}