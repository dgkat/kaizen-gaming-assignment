package com.dgkatoudis.kaizengamingassignment.presentation.sports.composables

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dgkatoudis.kaizengamingassignment.R
import com.dgkatoudis.kaizengamingassignment.presentation.sports.UiSport
import com.dgkatoudis.kaizengamingassignment.util.SportsRowState

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SportsRow(
    sport: UiSport,
    sportsIndex: Int,
    onClick: (Int) -> Unit,
    onFavoriteIconClick: (Int, Int) -> Unit
) {
    val listState = rememberLazyListState()
    var currentState by remember { mutableStateOf(sport.expanded) }
    val transition = updateTransition(currentState, label = "")
    val rotation by transition.animateFloat(label = "") { state ->
        when (state) {
            SportsRowState.Expanded -> 180f
            SportsRowState.Collapsed -> 0f
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.primaryVariant)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier,
                    painter = painterResource(
                        id = when (sport.id) {
                            "FOOT" -> R.drawable.football_2_64
                            "BASK" -> R.drawable.basketball_64
                            else -> R.drawable.tennis_64
                        }
                    ),
                    contentDescription = "sportIcon",
                    tint = MaterialTheme.colors.onPrimary
                )
                Text(
                    text = sport.title,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
            }

            IconButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick = {
                    onClick(sportsIndex)
                    currentState = if (currentState == SportsRowState.Expanded) {
                        SportsRowState.Collapsed
                    } else {
                        SportsRowState.Expanded
                    }
                }) {
                Icon(
                    modifier = Modifier.rotate(rotation),
                    imageVector = Icons.Filled.ExpandMore,
                    contentDescription = "expandMore",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }


        transition.AnimatedContent(
            transitionSpec = {
                slideInVertically { height -> -height } + fadeIn() with
                        slideOutVertically { height -> height } + fadeOut()
            }
        ) { expanded ->
            if (expanded == SportsRowState.Expanded) {
                LazyRow(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .wrapContentHeight(),
                    contentPadding = PaddingValues(start = 8.dp, end = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    itemsIndexed(
                        items = sport.events,
                        key = { index, _ ->
                            index
                        }
                    ) { index, sportEvent ->
                        SportEvent(
                            sportEvent = sportEvent,
                            sportsIndex = sportsIndex,
                            sportEventIndex = index,
                            onFavoriteIconClick = onFavoriteIconClick
                        )
                    }
                }
            }
        }
    }
}