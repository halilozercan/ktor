/*
 * Copyright 2014-2020 JetBrains s.r.o and contributors. Use of this source code is governed by the Apache 2.0 license.
 */

package io.ktor.utils.io

import kotlinx.atomicfu.*
import kotlin.reflect.*


inline operator fun AtomicInt.setValue(any: Any, property: KProperty<*>, newValue: Int) {
    value = newValue
}

inline operator fun AtomicInt.getValue(any: Any, property: KProperty<*>): Int = value
