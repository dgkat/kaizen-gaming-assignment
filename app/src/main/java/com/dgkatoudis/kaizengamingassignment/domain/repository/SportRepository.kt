package com.dgkatoudis.kaizengamingassignment.domain.repository

import com.dgkatoudis.kaizengamingassignment.domain.model.Resource
import com.dgkatoudis.kaizengamingassignment.domain.model.Sport

interface SportRepository {
    suspend fun getSports(): Resource<List<Sport>>
}