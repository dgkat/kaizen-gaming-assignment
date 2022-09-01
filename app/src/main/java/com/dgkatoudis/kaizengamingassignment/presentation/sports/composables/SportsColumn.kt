package com.dgkatoudis.kaizengamingassignment.presentation.sports.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dgkatoudis.kaizengamingassignment.R
import com.dgkatoudis.kaizengamingassignment.presentation.sports.SportsUiState
import com.dgkatoudis.kaizengamingassignment.presentation.sports.UiSport

@Composable
fun ScaffoldWithTopBar(
    onExpandIconClick: (Int) -> Unit,
    onFavoriteIconClick: (Int, Int) -> Unit,
    sportList: List<UiSport>,
    sportsUiState: SportsUiState
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.top_app_bar_title))
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
                backgroundColor = MaterialTheme.colors.secondary,
                contentColor = MaterialTheme.colors.onSecondary,
                elevation = 10.dp
            )
        }, content = {
            when (sportsUiState) {
                SportsUiState.SUCCESS -> {
                    LazyColumn(
                        state = rememberLazyListState(),
                        modifier = Modifier
                            .fillMaxSize(),
                        contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        itemsIndexed(sportList) { index, sport ->
                            SportsRow(
                                sport = sport,
                                sportsIndex = index,
                                onClick = onExpandIconClick,
                                onFavoriteIconClick = onFavoriteIconClick
                            )
                        }
                    }
                }
                SportsUiState.LOADING -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                }
                SportsUiState.ERROR -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = stringResource(R.string.data_load_error),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        })
}