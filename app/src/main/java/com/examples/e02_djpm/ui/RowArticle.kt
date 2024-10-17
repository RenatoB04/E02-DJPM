package com.examples.e02_djpm.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.examples.e02_djpm.models.Article
import com.examples.e02_djpm.ui.theme.E02DJPMTheme
import java.util.Date

@Composable
fun RowArticle(modifier: Modifier = Modifier, article: Article) {
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp)) {

        Column(modifier = Modifier
            .fillMaxWidth()) {

            Text(
                text = article.title ?: "No Title Available",
                style = MaterialTheme.typography.titleLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RowArticlePreview() {
    E02DJPMTheme {
        RowArticle(article = Article(
            "Sample Title",
            null,
            null,
            "url",
            Date()
        ))
    }
}