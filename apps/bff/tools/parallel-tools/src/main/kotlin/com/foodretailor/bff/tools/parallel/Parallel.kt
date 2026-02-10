package com.foodretailor.bff.tools.parallel

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withTimeout

@PublishedApi
internal const val PARALLEL_TIMEOUT_MS: Long = 900

suspend inline fun <T> parallel(
    crossinline block: suspend kotlinx.coroutines.CoroutineScope.() -> T,
): T =
    withTimeout(PARALLEL_TIMEOUT_MS) {
        coroutineScope { block() }
    }