package com.dgkatoudis.kaizengamingassignment.data.mappers

import com.dgkatoudis.kaizengamingassignment.data.model.RemoteSportEvent
import com.dgkatoudis.kaizengamingassignment.domain.model.SportEvent

class RemoteToDomainSportEventsMapper {
    fun map(remoteSportEvents: List<RemoteSportEvent>): List<SportEvent> {
        return remoteSportEvents.map {
            SportEvent(
                id = it.id,
                name = it.name,
                sportId = it.sportId,
                epoch = it.epoch
            )
        }
    }
}
