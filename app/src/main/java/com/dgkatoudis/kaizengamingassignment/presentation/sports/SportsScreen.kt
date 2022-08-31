package com.dgkatoudis.kaizengamingassignment.presentation.sports

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dgkatoudis.kaizengamingassignment.R
import com.dgkatoudis.kaizengamingassignment.util.SportsRowState


@Composable
fun SportScreen(
    viewModel: SportsViewModel = hiltViewModel()
) {
    ScaffoldWithTopBar(
        viewModel.sports
    )
}

@Composable
fun ScaffoldWithTopBar(
    sports: SportsUiState
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "My App")
                },
                actions = {
                    Row() {
                        IconButton(onClick = {}) {
                            Icon(Icons.Filled.AccountCircle, "accountCircleIcon")
                        }
                        IconButton(onClick = {}) {
                            Icon(Icons.Filled.Settings, "settingsIcon")
                        }
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 10.dp
            )
        }, content = {
            LazyColumn(
                state = rememberLazyListState(),
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(sports.data) { sport ->
                    SportsRow(sport = sport)
                }

            }
        })
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SportsRow(
    sport: UiSport,
    isExpanded: Boolean = true
) {
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
                    contentDescription = "expandMore",
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
                    state = rememberLazyListState(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .wrapContentHeight(),
                    contentPadding = PaddingValues(start = 8.dp, end = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(items = sport.events,
                        key = { sportEvent ->
                            sportEvent.id
                        })
                    { sportEvent ->
                        SportsEvent(teamName = UiTeams(sportEvent.team1, sportEvent.team2))
                    }
                }
            }
        }
    }
}

@Composable
fun SportsEvent(teamName: UiTeams) {
    Surface(
        modifier = Modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = 10.dp,
        color = MaterialTheme.colors.secondary
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Timer()
            Favorite()
            TeamName(teamName = teamName.team1)
            TeamName(teamName = teamName.team2)
        }
    }

}

@Composable
fun Timer() {
    Text(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(8.dp))
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.onSecondary,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        text = "HH/MM/SS",
        fontSize = 20.sp
    )

}

@Composable
fun Favorite() {
    Icon(
        modifier = Modifier,
        imageVector = Icons.Filled.Star,
        contentDescription = "star",
        tint = MaterialTheme.colors.onPrimary
    )

}

@Composable
fun TeamName(teamName: String) {
    Text(
        modifier = Modifier.width(140.dp),
        text = teamName,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 20.sp
    )
}













