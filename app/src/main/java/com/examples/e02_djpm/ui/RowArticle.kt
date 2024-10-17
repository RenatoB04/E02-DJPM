package com.examples.e02_djpm.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.examples.e02_djpm.R
import com.examples.e02_djpm.models.Article
import com.examples.e02_djpm.toYYYYMMDD
import com.examples.e02_djpm.ui.theme.E02DJPMTheme
import java.util.Date

@Composable
fun RowArticle(modifier: Modifier = Modifier, article: Article) {
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp)) {

        val imageUrl = article.urlToImage?.takeIf { it.isNotEmpty() && it != "null" }

        if (imageUrl != null) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "image article",
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                painter = painterResource(id = R.mipmap.img_place_holder),
                contentDescription = "image article",
                contentScale = ContentScale.Crop,
            )
        }

        Column(modifier = Modifier
            .padding(start = 8.dp)
            .fillMaxWidth()) {

            Text(
                text = article.title?.takeIf { it.isNotEmpty() && it != "null" } ?: "No Title Available",
                style = MaterialTheme.typography.titleLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = article.description?.takeIf { it.isNotEmpty() && it != "null" } ?: "No description available",
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = article.publishedAt?.toYYYYMMDD() ?: "Unknown Date",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RowArticlePreview() {
    E02DJPMTheme {
        RowArticle(article = Article(
            "Title",
            "description",
            null,
            "url",
            Date()
        ))
    }
}