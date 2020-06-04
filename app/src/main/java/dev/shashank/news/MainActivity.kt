package dev.shashank.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.mutableStateOf
import androidx.compose.setValue
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Text
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.Button
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.material.lightColorPalette
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

const val COULSON = "Boo Yah! - Son of a Coul"

val green = Color(0xFF1EB980)
private val themeColors = lightColorPalette(
    primary = green,
    surface = Color.DarkGray,
    onSurface = green
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAppTheme {
                MyScreenContent(listOf("Tony", "Steve", "Thor"))
            }
        }
    }
}

@Composable
fun MyAppTheme(children: @Composable() () -> Unit) {
    MaterialTheme(colors = themeColors) {
        Surface {
            children()
        }
    }
}

@Composable
fun MyScreenContent(
    names: List<String>,
    cstate: CounterState = CounterState(0)
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalGravity = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.weight(1f)) {
            names.forEachIndexed() { index, name ->
                Greeting("${index + 1} :P $name")
                Divider(color = Color.Black)
            }
            Divider(color = Color.Transparent, thickness = 32.dp)
        }
        Counter(cstate)
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        modifier = Modifier.padding(24.dp),
        style = MaterialTheme.typography.h4.copy()
    )
}

@Composable
fun Counter(state: CounterState) {
    Button(
        onClick = { state.count++ }
    ) {
        Text("Click Count: ${state.count}")
    }
}

@Preview(name = "Text Preview", showDecoration = true)
@Composable
fun DefaultPreview() {
    MyAppTheme {
        MyScreenContent(listOf("Peter"))
    }
}

class CounterState(count: Int) {
    var count by mutableStateOf(count)
}
