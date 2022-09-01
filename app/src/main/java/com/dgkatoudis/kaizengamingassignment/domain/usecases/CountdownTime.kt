package com.dgkatoudis.kaizengamingassignment.domain.usecases

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CountdownTime {
    operator fun invoke(): Flow<Long> {
        return flow {
            while (true) {
                emit(-1)
                delay(1_000)
            }
        }
    }
}