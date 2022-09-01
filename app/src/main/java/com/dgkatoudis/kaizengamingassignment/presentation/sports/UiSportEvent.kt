package com.dgkatoudis.kaizengamingassignment.presentation.sports

data class UiSportEvent(
    val id : String,
    val team1: String,
    val team2: String,
    val sportId: String,
    val date : Long,
    val isFavorite:Boolean = false
)
