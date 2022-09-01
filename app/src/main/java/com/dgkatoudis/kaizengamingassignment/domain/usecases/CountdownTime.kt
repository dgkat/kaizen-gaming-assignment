package com.dgkatoudis.kaizengamingassignment.domain.usecases

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CountdownTime(private val timer: Long) {
    private val time = timer
    operator fun invoke(): Flow<Long> {
        var countdown = time
        return flow {
            while (timer > 0) {
                countdown -= 1000
                emit(countdown)
                delay(1000)
            }
        }
    }
}