package com.dgkatoudis.kaizengamingassignment.presentation.sports

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dgkatoudis.kaizengamingassignment.presentation.sports.composables.ScaffoldWithTopBar


@Composable
fun SportScreen(
    viewModel: SportsViewModel = hiltViewModel()
) {

    ScaffoldWithTopBar(
        onExpandIconClick = viewModel::setExpanded,
        onFavoriteIconClick = viewModel::setFavorite,
        sportList = viewModel.sportsList,
        sportsUiState = viewModel.uiState.value
    )
}

