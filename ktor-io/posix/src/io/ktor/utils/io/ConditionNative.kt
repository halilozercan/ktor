package io.ktor.utils.io

import kotlinx.atomicfu.*
import kotlinx.coroutines.*
import kotlin.coroutines.*

internal actual class Condition actual constructor(val predicate: () -> Boolean) {
    private var _continuation = atomic<Continuation<Unit>?>(null)

    private var continuation: Continuation<Unit>?
        get() = _continuation.value
        set(value) {
            _continuation.value = value
        }

    actual fun check(): Boolean {
        return predicate()
    }

    actual fun signal() {
        val cont = continuation
        if (cont != null && predicate()) {
            this.continuation = null
            cont.resume(Unit)
        }
    }

    actual suspend fun await(block: () -> Unit) {
        if (predicate()) return

        return suspendCancellableCoroutine { c ->
            continuation = c
            block()
        }
    }

    actual suspend fun await() {
        if (predicate()) return

        return suspendCancellableCoroutine { c ->
            continuation = c
        }
    }
}
