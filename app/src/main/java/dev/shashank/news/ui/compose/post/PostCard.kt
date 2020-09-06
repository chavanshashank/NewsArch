package dev.shashank.news.ui.compose.post

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.EmphasisAmbient
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideEmphasis
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import dev.shashank.news.model.Article
import dev.shashank.news.ui.compose.image.NetworkImageComponentGlide

@Composable
fun PostCardTop(post: Article) {
    val typography = MaterialTheme.typography

    Card(
        shape = RoundedCornerShape(4.dp), backgroundColor = Color.White,
        modifier = Modifier.fillMaxWidth() + Modifier.padding(4.dp),
        elevation = Dp(5f)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            post.urlToImage?.let { imageUrl ->

                NetworkImageComponentGlide(imageUrl)
            }

            Spacer(Modifier.preferredHeight(16.dp))

            val emphasisLevels = EmphasisAmbient.current
            ProvideEmphasis(emphasisLevels.high) {
                post.title?.let {
                    Text(
                        text = it,
                        style = typography.h6
                    )
                }
                post.author?.let {
                    Text(
                        text = it,
                        style = typography.body2
                    )
                }
            }
            ProvideEmphasis(emphasisLevels.medium) {
                Text(
                    text = "${post.publishedAt}",
                    style = typography.body2
                )
            }
        }
    }
}

@Preview("Default colors", showDecoration = true)
@Composable
fun TutorialPreview() {
    TutorialPreviewTemplate()
}

@Composable
fun TutorialPreviewTemplate() {
    MaterialTheme {
        Surface {
            PostCardTop(
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
        }
    }
}
