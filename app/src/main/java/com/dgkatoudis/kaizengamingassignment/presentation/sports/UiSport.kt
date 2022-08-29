package com.dgkatoudis.kaizengamingassignment.presentation.sports

data class UiSport(
    val id: String,
    val title: String,
    val events: List<UiSportEvent>
)
