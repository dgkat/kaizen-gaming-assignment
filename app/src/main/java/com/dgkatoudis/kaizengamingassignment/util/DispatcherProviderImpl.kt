package com.dgkatoudis.kaizengamingassignment.util

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DispatcherProviderImpl @Inject constructor() : DispatcherProvider {

    override val main: CoroutineContext
        get() = Dispatchers.Main
    override val default: CoroutineContext
        get() = Dispatchers.Default
    override val io: CoroutineContext
        get() = Dispatchers.IO
}