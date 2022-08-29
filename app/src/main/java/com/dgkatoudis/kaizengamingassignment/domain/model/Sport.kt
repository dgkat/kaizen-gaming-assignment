package com.dgkatoudis.kaizengamingassignment.domain.model

data class Sport(
    val id : String ,
    val title : String,
    val events : List<SportEvent>
)
