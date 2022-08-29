package com.dgkatoudis.kaizengamingassignment.domain.usecases

import com.dgkatoudis.kaizengamingassignment.domain.model.Resource
import com.dgkatoudis.kaizengamingassignment.domain.model.Sport
import com.dgkatoudis.kaizengamingassignment.domain.repository.SportRepository

class GetSportsWithEvents(
    private val sportRepository: SportRepository
) {
    suspend operator fun invoke(): Resource<List<Sport>> {
        return sportRepository.getSports()
    }
}