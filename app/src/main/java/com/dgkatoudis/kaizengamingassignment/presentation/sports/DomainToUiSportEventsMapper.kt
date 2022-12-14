package com.dgkatoudis.kaizengamingassignment.presentation.sports

import com.dgkatoudis.kaizengamingassignment.domain.model.SportEvent
import com.dgkatoudis.kaizengamingassignment.domain.usecases.CountdownTime
import kotlinx.coroutines.flow.map


class DomainToUiSportEventsMapper(
    private val dateFormatter: DateFormatter
) {
    fun map(events: List<SportEvent>): List<UiSportEvent> {
        return events.map { it ->
            val teams = getTeams(it.name)
            UiSportEvent(
                id = it.id,
                team1 = teams.team1,
                team2 = teams.team2,
                sportId = it.sportId,
                date = CountdownTime(it.epoch).invoke().map(dateFormatter::format)
            )
        }
    }

    private fun getTeams(sportEventName: String): UiTeams {
        val teams = sportEventName.split("-")

        return if (teams.size <= 1) {
            UiTeams(team1 = teams[0].trim(), team2 = "")
        } else {
            UiTeams(team1 = teams[0].trim(), team2 = teams[1].trim())
        }
    }
}