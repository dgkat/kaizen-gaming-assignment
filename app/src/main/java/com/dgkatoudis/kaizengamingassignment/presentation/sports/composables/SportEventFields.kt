package com.dgkatoudis.kaizengamingassignment.presentation.sports.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun Timer(date: String) {
    Text(
        modifier = Modifier
            .clip(shape = MaterialTheme.shapes.medium)
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.onPrimary,
                shape = MaterialTheme.shapes.medium
            )
            .padding(8.dp),
        text = date,
        color = MaterialTheme.colors.onPrimary
    )

}

@Composable
fun Favorite(
    isFavorite: Boolean = false,
    sportsIndex: Int,
    sportEventIndex: Int,
    onFavoriteIconClick: (Int, Int) -> Unit
) {
    IconButton(onClick = {
        onFavoriteIconClick(sportsIndex, sportEventIndex)
    }) {
        Icon(
            modifier = Modifier,
            imageVector = if (isFavorite) {
                Icons.Filled.Star
            } else {
                Icons.Outlined.Star
            },
            contentDescription = "star",
            tint = if (isFavorite) {
                Color.Yellow
            } else {
                MaterialTheme.colors.onPrimary
            }
        )
    }
}

@Composable
fun TeamName(teamName: String) {
    Text(
        modifier = Modifier.width(140.dp),
        text = teamName,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = MaterialTheme.colors.onPrimary
    )
}