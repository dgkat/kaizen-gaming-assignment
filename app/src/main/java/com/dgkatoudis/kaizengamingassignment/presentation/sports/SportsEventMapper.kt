package com.dgkatoudis.kaizengamingassignment.presentation.sports

import com.dgkatoudis.kaizengamingassignment.domain.model.SportEvent

class SportsEventMapper(
    private val dateFormatter : DateFormatter
) {
    fun map(events : List<SportEvent>):List<UiSportEvent>{
        return events.map {
            UiSportEvent(
                id = it.id,
                name = it.name,
                sportId = it.sportId,
                date = dateFormatter.format(it.epoch)
            )
        }
    }
}