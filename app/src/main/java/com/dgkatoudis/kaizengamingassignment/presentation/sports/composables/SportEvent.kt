package com.dgkatoudis.kaizengamingassignment.presentation.sports.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dgkatoudis.kaizengamingassignment.R
import com.dgkatoudis.kaizengamingassignment.presentation.sports.UiSportEvent

@Composable
fun SportEvent(
    sportEvent: UiSportEvent,
    sportsIndex: Int,
    sportEventIndex: Int,
    onFavoriteIconClick: (Int, Int) -> Unit
) {
    val countDownFlow = sportEvent.date.collectAsState(initial = stringResource(R.string.date))

    Surface(
        modifier = Modifier,
        shape = MaterialTheme.shapes.large,
        elevation = 10.dp,
        color = MaterialTheme.colors.primary
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Timer(countDownFlow.value)
            Favorite(
                isFavorite = sportEvent.isFavorite,
                sportsIndex = sportsIndex,
                sportEventIndex = sportEventIndex,
                onFavoriteIconClick = onFavoriteIconClick
            )
            TeamName(teamName = sportEvent.team1)
            TeamName(teamName = sportEvent.team2)
        }
    }

}