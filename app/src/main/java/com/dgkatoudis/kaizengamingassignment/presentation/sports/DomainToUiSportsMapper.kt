package com.dgkatoudis.kaizengamingassignment.presentation.sports

import com.dgkatoudis.kaizengamingassignment.domain.model.Sport

class DomainToUiSportsMapper(
    private val domainToUiSportEventsMapper: DomainToUiSportEventsMapper
) {
    fun map(sports: List<Sport>): List<UiSport> {
        return sports.map {
            UiSport(
                id = it.id,
                title = it.title,
                events = domainToUiSportEventsMapper.map(it.events)
            )
        }
    }
}