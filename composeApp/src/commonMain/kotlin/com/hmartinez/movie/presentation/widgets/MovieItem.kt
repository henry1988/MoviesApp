package com.hmartinez.movie.presentation.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.hmartinez.movie.domain.models.Movie
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun MovieItem(movie: Movie, onItemClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onItemClick(movie.id) }
            .padding(8.dp)
    ) {

        val asyncPainterResource = asyncPainterResource(data = movie.poster)

        // Movie Poster
        KamelImage(
            resource = asyncPainterResource,
            contentDescription = movie.name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2 / 3f), // Adjust aspect ratio as needed
            contentScale = ContentScale.Crop,
            onLoading = {
                // Composable to display while loading
                CircularProgressIndicator()
            },
            onFailure = {
                // Composable to display on loading failure
                Text("Image loading failed")
            }
        )

        // Title and Release Year
        Column(modifier = Modifier.padding(top = 8.dp)) {
            Text(
                text = movie.name,
                style = MaterialTheme.typography.h2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = movie.releaseDate.take(4), // Extract year from release date
                style = MaterialTheme.typography.body1
            )
        }
    }
}