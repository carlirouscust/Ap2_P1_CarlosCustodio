package edu.ucne.ap2_p1_carloscustodio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import edu.ucne.ap2_p1_carloscustodio.ui.theme.Ap2_P1_CarlosCustodioTheme
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.ap2_p1_carloscustodio.Presentation.navigation.HostNavigation

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ap2_P1_CarlosCustodioTheme {
                val navHost = rememberNavController()
                HostNavigation(navHost)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Ap2_P1_CarlosCustodioTheme {
        Greeting("Android")
    }
}