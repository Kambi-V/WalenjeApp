package kambi.victor.walenje

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kambi.victor.walenje.core.designsystem.theme.WalenjeTheme
import kambi.victor.walenje.navigation.WalenjeNavGraph
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import walenjeapp.composeapp.generated.resources.Res
import walenjeapp.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
  WalenjeTheme { WalenjeNavGraph() }
}

@Composable
fun InitialScreen(onNavigateToWelcome: () -> Unit) {
  var showContent by remember { mutableStateOf(false) }
  Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
    Button(onClick = { showContent = !showContent }) { Text("Click me!") }
    Spacer(modifier = Modifier.padding(4.dp))
    Button(onClick = { onNavigateToWelcome() }) { Text("Navigate to Welcome") }
    AnimatedVisibility(showContent) {
      val greeting = remember { Greeting().greet() }
      Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painterResource(Res.drawable.compose_multiplatform), null)
        Text("Compose: $greeting")
      }
    }
  }
}