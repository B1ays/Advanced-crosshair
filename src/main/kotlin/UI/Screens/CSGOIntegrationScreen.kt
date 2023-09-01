package UI.Screens

import Data.CSGOEvents
import Data.DefaultPadding
import Data.DialogState
import Data.NETCOMPORT_OPTION
import DataStore.Stores.EnableWithSniperRifleDS
import UI.Icons.Icons
import UI.Icons.icons.Copy
import UI.Icons.icons.Power
import UI.Theme.*
import Utils.copyToClipboard
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogWindow
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import config
import org.koin.compose.koinInject
import uk.oczadly.karl.csgsi.GSIServer
import uk.oczadly.karl.csgsi.config.GameNotFoundException
import java.io.IOException
import java.nio.file.Path

class CSGOIntegrationScreen: Screen {

    @Composable
    override fun Content() {
        var navigator: Navigator? = null
        val server: GSIServer = koinInject()
        val csgoEvents: CSGOEvents = koinInject()

        val gameStateContext by csgoEvents.context.collectAsState()
        val serverRunning = gameStateContext?.gsiServer?.isRunning ?: false
        val address = gameStateContext?.let {
            "${it.address?.hostAddress ?: ""}:${it.gsiServer?.port}"
        } ?: ""

        Column(
            modifier = Modifier
                .padding(DefaultPadding.CardDefaultPaddingBig)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .height(65.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.5F)
                        .background(backgroundColorDark, cardShapeMid),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .padding(DefaultPadding.CardDefaultPaddingMedium)
                            .size(10.dp)
                            .background(
                                if (serverRunning) Color.Green else Color.Red,
                                CircleShape
                            )
                            .clip(CircleShape)
                    )
                    Column(
                        modifier = Modifier.padding(DefaultPadding.CardDefaultPaddingSmall)
                    ) {
                        Text(
                            text = if (serverRunning) "Server running" else "Server not running",
                            color = accentLight,
                            style = MaterialTheme.typography.body1
                        )
                        if (address.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = address,
                                color = accentLight
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(backgroundColorDark, cardShapeMid)
                        .clip(cardShapeMid)
                        .toggleable(serverRunning) {
                            try {
                                if (server.isRunning) server.stop() else server.start()
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        imageVector = Icons.Power,
                        contentDescription = null,
                        tint = if (serverRunning) powerButtonEnabledUnfocused
                        else powerButtonDisabledUnfocused
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(backgroundColorDark, cardShapeMid)
                        .clip(cardShapeMid)
                        .clickable {
                            navigator?.push(CSGOIntegrationHelpSubscreen())
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "?",
                        fontSize = 50.sp,
                        textAlign = TextAlign.Center,
                        color = accentLight
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Navigator(
                CSGOIntegrationSettingsSubscreen()
            ) {
                CurrentScreen()
                navigator = it
            }
        }
    }
}

private class CSGOIntegrationSettingsSubscreen : Screen {
    @Composable
    override fun Content() {
        val enableWithSniperRifleDS = koinInject<EnableWithSniperRifleDS>()
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.7F)
                    .background(backgroundColorDark, cardShapeMid)
                    .padding(DefaultPadding.CardDefaultPaddingSmall),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.weight(0.5F),
                    text = "Enable crosshair only while sniper rifle in hands",
                    color = accentLight
                )
                Switch(
                    checked = enableWithSniperRifleDS.asState().value,
                    onCheckedChange = {
                        enableWithSniperRifleDS.value = it
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = accentPrimary,
                        uncheckedThumbColor = accentLight
                    )
                )
            }
        }
    }
}

private class CSGOIntegrationHelpSubscreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val scrollState = rememberScrollState()
        var dialogState by remember { mutableStateOf(DialogState()) }

        Column(
            modifier = Modifier
                .padding(DefaultPadding.CardDefaultPaddingMedium)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Text(
                "This is not a cheat, this just utilises the -netconport launch option which allows for applications to connect to the game and read state.",
                color = accentLight,
                style = MaterialTheme.typography.body2
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                color = accentDark
            )
            Text(
                "Add config file to CS:GO folder:",
                color = accentLight,
                style = MaterialTheme.typography.body2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Button(
                onClick = {
                    try {
                        // Automatically locates the game directory and creates the configuration file
                        val output: Path = config.writeFile("test_service")
                        dialogState = DialogState(
                            isVisible = true,
                            text = "Config created in path: $output"
                        )
                    } catch (e: GameNotFoundException) {
                        dialogState = DialogState(
                            isVisible = true,
                            text = "Couldn't locate CSGO installation: " + e.message
                        )
                        System.err.println("Couldn't locate CSGO installation: " + e.message)
                    } catch (e: IOException) {
                        dialogState = DialogState(
                            isVisible = true,
                            text = "Couldn't write to configuration file."
                        )
                        System.err.println("Couldn't write to configuration file.")
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = accentSecondary,
                )
            ) {
                Text(
                    "Add config",
                    color = accentLight
                )
            }
            Text(
                "Add this launch option for CS:GO:",
                color = accentLight,
                style = MaterialTheme.typography.body2
            )
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = TextFieldValue(NETCOMPORT_OPTION),
                onValueChange = {},
                readOnly = true,
                enabled = false,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    disabledTextColor = accentLight,
                    trailingIconColor = accentPrimary,
                    unfocusedBorderColor = accentDark
                ),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            copyToClipboard(NETCOMPORT_OPTION)
                        }
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(24.dp),
                            imageVector = Icons.Copy,
                            contentDescription = null,
                            tint = accentPrimary
                        )
                    }
                }
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                OutlinedButton(
                    onClick = navigator::popAll,
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor,
                        accentLight
                    ),
                    border = BorderStroke(1.dp, accentDark)
                ) {
                    Text(
                        text = "Back",
                        color = accentLight
                    )
                }
            }
        }
        DialogWindow(
            onCloseRequest = {
                dialogState = DialogState()
            },
            visible = dialogState.isVisible,
            title = "Config create"
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
                    .padding(DefaultPadding.CardDefaultPaddingMedium)
            ) {
                Text(
                    dialogState.text,
                    color = accentLight
                )
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )
                Button(
                    onClick = {
                        dialogState = DialogState()
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = accentSecondary,
                    )
                ) {
                    Text(
                        text = "Ok",
                        color = accentLight
                    )
                }
            }
        }
    }
}
