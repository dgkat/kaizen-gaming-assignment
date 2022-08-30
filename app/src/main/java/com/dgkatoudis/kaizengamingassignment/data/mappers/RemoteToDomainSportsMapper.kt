package com.dgkatoudis.kaizengamingassignment.data.mappers

import com.dgkatoudis.kaizengamingassignment.data.model.RemoteSport
import com.dgkatoudis.kaizengamingassignment.domain.model.Sport

class RemoteToDomainSportsMapper(
    private val remoteToDomainSportEventsMapper: RemoteToDomainSportEventsMapper
) {

    fun map(remoteSports: List<RemoteSport>): List<Sport> {
        return remoteSports.map {
            Sport(
                id = it.id,
                title = it.title,
                events = remoteToDomainSportEventsMapper.map(it.events)
            )
        }
    }
}
