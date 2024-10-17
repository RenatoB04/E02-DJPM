package com.examples.e02_djpm.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.examples.e02_djpm.models.Article
import com.examples.e02_djpm.ui.theme.E02DJPMTheme
import java.util.Date

@Composable
fun RowArticle(modifier: Modifier = Modifier, article: Article) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(4.dp, RoundedCornerShape(8.dp)),
        color = MaterialTheme.colorScheme.background,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {

            Text(
                text = article.title ?: "No Title Available",
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Visible
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RowArticlePreview() {
    E02DJPMTheme {
        RowArticle(
            article = Article(
                "Titulo de exemplo. Este é um título que deverá ajustar dinamicamente o tamanho do botão para fazer com que caiba o título inteiro.",
                null,
                null,
                "url",
                Date()
            )
        )
    }
}