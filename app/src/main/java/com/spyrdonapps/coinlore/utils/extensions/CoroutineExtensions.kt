package com.spyrdonapps.coinlore.utils.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

fun CoroutineScope.intervalLaunch(periodMs: Long, block: suspend () -> Unit) {
    launch(Dispatchers.IO) {
        flow {
            while (true) {
                emit(Unit)
                delay(periodMs)
            }
        }.collect {
            block()
        }
    }
}
