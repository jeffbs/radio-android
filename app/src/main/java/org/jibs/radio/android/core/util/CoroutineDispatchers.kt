package org.jibs.radio.android.core.util

import kotlinx.coroutines.CoroutineDispatcher

data class CoroutineDispatchers(val io: CoroutineDispatcher, val main: CoroutineDispatcher)