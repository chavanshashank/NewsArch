package dev.shashank.news.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.AdapterList
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text
import androidx.ui.foundation.clickable
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Scaffold
import androidx.ui.material.Surface
import androidx.ui.material.TopAppBar
import androidx.ui.material.ripple.ripple
import androidx.ui.tooling.preview.Preview
import dev.shashank.news.model.Article
import dev.shashank.news.model.ViewState
import dev.shashank.news.ui.compose.loader.LiveDataLoadingComponent
import dev.shashank.news.ui.compose.post.PostCardTop
import dev.shashank.news.ui.viewmodels.NewsArticleViewModel
import org.koin.android.viewmodel.ext.android.viewModel

fun <T> LiveData<T>.observeNotNull(owner: LifecycleOwner, observer: (T) -> Unit) {
    this.observe(owner, Observer { it?.let(observer) })
}

class MainActivity : AppCompatActivity() {

    private val newsArticleViewModel: NewsArticleViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsArticleViewModel.getNewsArticles().observeNotNull(this) { state ->
            when (state) {
                is ViewState.Success -> setContent { // todo not sure about having two setContents, need to check
                    MyAppTheme {
                        LiveDataComponentList(
                            state.data
                        )
                    }
                }
                is ViewState.Loading -> setContent {
                    MyAppTheme {
                        LiveDataLoadingComponent()
                    }
                }
            }
        }
    }
}

@Composable
fun MyAppTheme(children: @Composable() () -> Unit) {
    MaterialTheme {
        Scaffold(topAppBar = { TopAppBar(title = { Text("News Arch") }) }) {
            Surface {
                children()
            }
        }
    }
}

@Composable
fun LiveDataComponentList(articleList: List<Article>) {
    AdapterList(data = articleList) { article ->
        Box(Modifier.ripple().clickable(onClick = {}),
            children = {
                PostCardTop(post = article)
            })
    }
}

@Preview(name = "Text Preview", showBackground = true)
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

@Preview
@Composable
fun LiveDataLoadingComponentPreview() {
    LiveDataLoadingComponent()
}
