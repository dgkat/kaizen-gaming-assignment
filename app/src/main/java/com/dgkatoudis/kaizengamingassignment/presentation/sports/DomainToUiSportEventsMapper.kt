package com.dgkatoudis.kaizengamingassignment.presentation.sports

import com.dgkatoudis.kaizengamingassignment.domain.model.SportEvent

class DomainToUiSportEventsMapper(
    /*private val dateFormatter: DateFormatter*/
) {
    fun map(events: List<SportEvent>): List<UiSportEvent> {
        return events.map {
            val teams = getTeams(it.name)
            UiSportEvent(
                id = it.id,
                team1 = teams.team1,
                team2 = teams.team2,
                sportId = it.sportId,
                date = it.epoch
            )
        }
    }

    private fun getTeams(sportEventName: String): UiTeams {
        val teams = sportEventName.split("-")

        return if (teams.size<=1) {
            UiTeams(team1 = teams[0].trim(), team2 = "")
        } else {
            UiTeams(team1 = teams[0].trim(), team2 = teams[1].trim())
        }
    }
}