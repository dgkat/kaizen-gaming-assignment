package com.dgkatoudis.kaizengamingassignment.presentation.sports

import com.dgkatoudis.kaizengamingassignment.domain.model.Sport

class SportsMapper(
    private val sportsEventMapper: SportsEventMapper
) {
    fun map(sports: List<Sport>): List<UiSport> {
        return sports.map {
            UiSport(
                id = it.id,
                title = it.title,
                events = sportsEventMapper.map(it.events)
            )
        }
    }
}