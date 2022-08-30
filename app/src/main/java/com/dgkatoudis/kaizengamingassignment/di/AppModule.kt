package com.dgkatoudis.kaizengamingassignment.di

import com.dgkatoudis.kaizengamingassignment.data.repository.SportRepositoryImpl
import com.dgkatoudis.kaizengamingassignment.domain.repository.SportRepository
import com.dgkatoudis.kaizengamingassignment.domain.usecases.GetSportsWithEvents
import com.dgkatoudis.kaizengamingassignment.presentation.sports.DateFormatter
import com.dgkatoudis.kaizengamingassignment.presentation.sports.SportsEventMapper
import com.dgkatoudis.kaizengamingassignment.presentation.sports.SportsMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDateFormatter():DateFormatter{
        return DateFormatter()
    }

    @Provides
    fun provideSportsEventMapper(dateFormatter: DateFormatter): SportsEventMapper {
        return SportsEventMapper(dateFormatter)
    }

    @Provides
    fun provideSportsMapper(sportsEventMapper: SportsEventMapper):SportsMapper{
        return SportsMapper(sportsEventMapper)
    }

    @Provides
    fun provideGetSportsWithEvents(repository: SportRepository):GetSportsWithEvents{
        return GetSportsWithEvents(repository)
    }
    @Provides
    fun provideSportsRepository():SportRepository{
        return SportRepositoryImpl()
    }
}