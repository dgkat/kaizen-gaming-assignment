package com.dgkatoudis.kaizengamingassignment.di

import android.app.Application
import com.dgkatoudis.kaizengamingassignment.data.mappers.RemoteToDomainSportEventsMapper
import com.dgkatoudis.kaizengamingassignment.data.mappers.RemoteToDomainSportsMapper
import com.dgkatoudis.kaizengamingassignment.data.repository.SportRepositoryImpl
import com.dgkatoudis.kaizengamingassignment.domain.repository.SportRepository
import com.dgkatoudis.kaizengamingassignment.domain.usecases.CountdownTime
import com.dgkatoudis.kaizengamingassignment.domain.usecases.GetSportsWithEvents
import com.dgkatoudis.kaizengamingassignment.presentation.sports.DateFormatter
import com.dgkatoudis.kaizengamingassignment.presentation.sports.DomainToUiSportEventsMapper
import com.dgkatoudis.kaizengamingassignment.presentation.sports.DomainToUiSportsMapper
import com.dgkatoudis.kaizengamingassignment.util.DispatcherProvider
import com.dgkatoudis.kaizengamingassignment.util.DispatcherProviderImpl
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDateFormatter(): DateFormatter {
        return DateFormatter()
    }

    @Provides
    fun provideSportsEventMapper(dateFormatter: DateFormatter): DomainToUiSportEventsMapper {
        return DomainToUiSportEventsMapper(dateFormatter)
    }

    @Provides
    fun provideSportsMapper(domainToUiSportEventsMapper: DomainToUiSportEventsMapper): DomainToUiSportsMapper {
        return DomainToUiSportsMapper(domainToUiSportEventsMapper)
    }

    @Provides
    fun provideGetSportsWithEvents(repository: SportRepository): GetSportsWithEvents {
        return GetSportsWithEvents(repository)
    }

    @Provides
    fun provideSportsRepository(
        moshi: Moshi,
        context: Application,
        remoteToDomainSportsMapper: RemoteToDomainSportsMapper,
        dispatcherProvider: DispatcherProvider
    ): SportRepository {
        return SportRepositoryImpl(moshi, context, remoteToDomainSportsMapper, dispatcherProvider)
    }

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    fun provideRemoteToDomainSportEventMapper(): RemoteToDomainSportEventsMapper {
        return RemoteToDomainSportEventsMapper()
    }

    @Provides
    fun provideRemoteToDomainSportsMapper(
        remoteToDomainSportEventsMapper: RemoteToDomainSportEventsMapper
    ): RemoteToDomainSportsMapper {
        return RemoteToDomainSportsMapper(remoteToDomainSportEventsMapper)
    }

    @Provides
    fun provideCountdownTime(timer: Long): CountdownTime {
        return CountdownTime(timer)
    }

    @Provides
    fun provideDispatcherProvider(): DispatcherProvider {
        return DispatcherProviderImpl()
    }
}