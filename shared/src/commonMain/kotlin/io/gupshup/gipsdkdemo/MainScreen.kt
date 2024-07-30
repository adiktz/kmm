import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.gupshup.gipsdkdemo.getPlatform
import io.gupshup.gipsdkdemo.platform.GipChatHelper
import kotlinx.coroutines.channels.Channel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Preview()
@Composable
fun MainScreen(
    modifier: Modifier,
    channel: Channel<String>,
    isGipInitialized: Boolean,
    setInitialized: (Boolean) -> Unit,
    gipChat: GipChatHelper = koinInject()
) {
    var appId by remember { mutableStateOf("c5453fbf-95e6-4330-9e14-28a85d3ea6a5") }
    var userName by remember { mutableStateOf("Gaurav") }
    var userId by remember { mutableStateOf("Test-User-Id-1234") }

    val title = remember { getPlatform().title }


    val listener: (String) -> Unit = {
        channel.trySend(it)
    }
    gipChat.onError(listener)

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current


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
        OutlinedTextField(
            value = appId,
            onValueChange = { appId = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .focusRequester(focusRequester),
            singleLine = true,
            label = { Text("App ID") }
        )
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .focusRequester(focusRequester),
            singleLine = true,
            label = { Text("User Name") }
        )
        OutlinedTextField(
            value = userId,
            onValueChange = { userId = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .focusRequester(focusRequester),
            singleLine = true,
            label = { Text("User ID") }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ElevatedButton(
                onClick = {
                    focusRequester.captureFocus()
                    initializeGipSdk(
                        gipChat = gipChat,
                        appId = appId,
                        userName = userName,
                        userId = userId,
                        onInitialized = {
                            setInitialized(it)
                        }
                    )
                },
                enabled = userName.isNotEmpty() && userId.isNotEmpty()
            ) {
                if (isGipInitialized)
                    Text(text = "Re-Initialize".uppercase())
                else Text(text = "Initialize".uppercase())
            }
            ElevatedButton(onClick = {
                focusManager.clearFocus()
                userName = ""
                userId = ""
                initializeGipSdk(
                    gipChat = gipChat,
                    appId = appId,
                    userName = null,
                    userId = null,
                    onInitialized = {
                        setInitialized(it)
                    }
                )
            }) {
                Text(text = "Anonymous".uppercase())
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        ElevatedButton(
            onClick = { gipChat.show() },
            enabled = isGipInitialized
        ) {
            Text(text = "Start Chat".uppercase())
        }

        Spacer(modifier = Modifier.weight(0.3f))
    }
}

internal fun initializeGipSdk(
    gipChat: GipChatHelper,
    appId: String,
    userName: String? = null,
    userId: String? = null,
    onInitialized: ((Boolean) -> Unit)? = null
) {
    onInitialized?.invoke(false)
    gipChat.setAppId(appId)
    gipChat.setUserName(userName)
    gipChat.setUserId(userId)
    gipChat.initialize { value ->
        onInitialized?.invoke(value)
    }
}
