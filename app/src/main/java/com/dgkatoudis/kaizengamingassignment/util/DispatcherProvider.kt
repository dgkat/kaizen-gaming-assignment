package com.dgkatoudis.kaizengamingassignment.util

import kotlin.coroutines.CoroutineContext

interface DispatcherProvider {

    val main: CoroutineContext
    val default: CoroutineContext
    val io: CoroutineContext
}