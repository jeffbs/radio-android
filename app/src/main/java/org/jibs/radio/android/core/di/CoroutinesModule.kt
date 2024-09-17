package org.jibs.radio.android.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import org.jibs.radio.android.core.util.CoroutineDispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesModule {

    @Provides
    @Singleton
    fun provideCoroutineDispatchers(
    ): CoroutineDispatchers {
        return CoroutineDispatchers(io = Dispatchers.IO, main = Dispatchers.Main)
    }
}
