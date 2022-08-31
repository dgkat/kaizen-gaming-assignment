package com.dgkatoudis.kaizengamingassignment.presentation.sports

import com.dgkatoudis.kaizengamingassignment.util.SportsRowState

data class UiSport(
    val id: String,
    val title: String,
    val events: List<UiSportEvent>,
    val expanded : SportsRowState = SportsRowState.Expanded
)
