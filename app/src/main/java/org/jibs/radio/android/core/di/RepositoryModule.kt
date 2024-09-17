package org.jibs.radio.android.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.jibs.radio.android.core.util.NetworkUtils
import org.jibs.radio.android.data.local.dao.StationDao
import org.jibs.radio.android.data.remote.ApiService
import org.jibs.radio.android.data.repository.StationRepositoryImpl
import org.jibs.radio.android.domain.repository.StationRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideStationRepository(
        stationDao: StationDao,
        apiService: ApiService,
        networkUtils: NetworkUtils
    ): StationRepository {
        return StationRepositoryImpl(stationDao, apiService, networkUtils)
    }
}