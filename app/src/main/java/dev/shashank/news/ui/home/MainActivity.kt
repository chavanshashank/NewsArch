package dev.shashank.news.ui.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.mutableStateOf
import androidx.compose.setValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.AdapterList
import androidx.ui.foundation.Box
import androidx.ui.foundation.ContentGravity
import androidx.ui.graphics.Color
import androidx.ui.layout.fillMaxSize
import androidx.ui.layout.wrapContentWidth
import androidx.ui.material.CircularProgressIndicator
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.material.lightColorPalette
import androidx.ui.tooling.preview.Preview
import dev.shashank.news.model.Article
import dev.shashank.news.model.ViewState
import dev.shashank.news.viewmodels.NewsArticleViewModel
import org.koin.android.viewmodel.ext.android.viewModel

val green = Color(0xFF1EB980)
private val themeColors = lightColorPalette(
    primary = green
)

fun <T> LiveData<T>.observeNotNull(owner: LifecycleOwner, observer: (T) -> Unit) {
    this.observe(owner, Observer { it?.apply(observer) })
}

class MainActivity : AppCompatActivity() {

    private val newsArticleViewModel: NewsArticleViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Update the UI on state change
        /*newsArticleViewModel.getNewsArticles().observeNotNull(this) { state ->
            when (state) {
                is ViewState.Success -> Log.d("--data", state.data.toString())
            }
        }*/

        val nameObserver = Observer<ViewState<List<Article>>> {
            // Update the UI, in this case, a TextView.
            it?.apply {
                when (it) {
                    is ViewState.Success -> {
                        it.data.forEach {
                            Log.d("--article", it.toString())
                        }

                        setContent {
                            MyAppTheme {
                                LiveDataComponentList(
                                    it.data
                                )
                            }
                        }
                    }
                    is ViewState.Loading -> {
                        setContent {
                            MyAppTheme {
                                LiveDataLoadingComponent()
                            }
                        }
                    }
                }
            }
        }

        newsArticleViewModel.getNewsArticles().observe(this, nameObserver)
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

@Preview(name = "Text Preview", showDecoration = true)
@Composable
fun DefaultPreview() {
    MyAppTheme {
        LiveDataComponentList(
            listOf(
                Article(
                    author = "Shashank",
                    content = "Content",
                    description = "Description",
                    publishedAt = "1234567890",
                    title = "Title",
                    url = "hackernews.com/abcd/",
                    urlToImage = "hackernews.com/123.jpg"
                )
            )
        )
    }
}

class CounterState(count: Int) {
    var count by mutableStateOf(count)
}

@Composable
fun LiveDataComponentList(personList: List<Article>) {
    // AdapterList is a vertically scrolling list that only composes and lays out the currently
    // visible items. This is very similar to what RecylerView tries to do as it's more optimized
    // than the VerticalScroller.
    AdapterList(data = personList) { person ->
        // Card composable is a predefined composable that is meant to represent the
        // card surface as specified by the Material Design specification. We also
        // configure it to have rounded corners and apply a modifier.

        // You can think of Modifiers as implementations of the decorators pattern that are used to
        // modify the composable that its applied to. In this example, we assign a padding of
        // 16dp to the Card along with specifying it to occupy the entire available width.

        PostCardTop(post = person)
    }
}

// We represent a Composable function by annotating it with the @Composable annotation. Composable
// functions can only be called from within the scope of other composable functions. We should
// think of composable functions to be similar to lego blocks - each composable function is in turn
// built up of smaller composable functions.
@Composable
fun LiveDataLoadingComponent() {
    // Box is a predefined convenience composable that allows you to apply common draw & layout
    // logic. In addition we also pass a few modifiers to it.

    // You can think of Modifiers as implementations of the decorators pattern that are
    // used to modify the composable that its applied to. In this example, we configure the
    // Box composable to occupy the entire available width and height using
    // Modifier.fillMaxSize() and give center gravity to the content inside this box.
    Box(modifier = Modifier.fillMaxSize(), gravity = ContentGravity.Center) {
        // A pre-defined composable that's capable of rendering a circular progress indicator. It
        // honors the Material Design specification.
        CircularProgressIndicator(modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally))
    }
}

@Preview
@Composable
fun LiveDataLoadingComponentPreview() {
    LiveDataLoadingComponent()
}
