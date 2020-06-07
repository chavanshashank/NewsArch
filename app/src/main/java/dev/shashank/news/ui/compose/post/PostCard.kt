package dev.shashank.news.ui.compose.post

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Spacer
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.layout.preferredHeight
import androidx.ui.material.Card
import androidx.ui.material.EmphasisAmbient
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ProvideEmphasis
import androidx.ui.material.Surface
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.Dp
import androidx.ui.unit.dp
import dev.shashank.news.model.Article
import dev.shashank.news.ui.compose.image.NetworkImageComponentGlide

@Composable
fun PostCardTop(post: Article) {
    val typography = MaterialTheme.typography

    Card(
        shape = RoundedCornerShape(4.dp), color = Color.White,
        modifier = Modifier.fillMaxWidth() + Modifier.padding(4.dp),
        elevation = Dp(4f)
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
