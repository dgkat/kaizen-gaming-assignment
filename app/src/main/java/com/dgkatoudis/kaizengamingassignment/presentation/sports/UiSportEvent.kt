package com.dgkatoudis.kaizengamingassignment.presentation.sports

import kotlinx.coroutines.flow.Flow

data class UiSportEvent(
    val id: String,
    val team1: String,
    val team2: String,
    val sportId: String,
    val date: Flow<String>,
    val isFavorite: Boolean = false
)
