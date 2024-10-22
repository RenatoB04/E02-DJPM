package com.examples.e02_djpm.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
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
    Row(
        modifier = modifier
            .padding(8.dp)  // Espaçamento entre os itens
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color.Gray), RoundedCornerShape(8.dp)) // Borda arredondada
            .clip(RoundedCornerShape(8.dp))
            .padding(8.dp) // Padding interno dentro da borda
    ) {
        article.urlToImage?.let {
            AsyncImage(
                model = it,
                contentDescription = "image article",
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .padding(6.dp)
                    .shadow(4.dp, RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
        } ?: run {
            Image(
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .padding(6.dp)
                    .shadow(4.dp, RoundedCornerShape(8.dp)),
                painter = painterResource(id = R.mipmap.img_place_holder),
                contentDescription = "image article",
                contentScale = ContentScale.Crop,
            )
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp)) {
            Text(
                text = article.title ?: "",
                style = MaterialTheme.typography.titleLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = article.description ?: "",
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
            Text(text = article.publishedAt?.toYYYYMMDD() ?: "")
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