package com.dgkatoudis.kaizengamingassignment.domain.usecases

import com.dgkatoudis.kaizengamingassignment.domain.model.Resource
import com.dgkatoudis.kaizengamingassignment.domain.model.Sport
import com.dgkatoudis.kaizengamingassignment.domain.repository.SportRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetSportsWithEventsTest {

    private lateinit var getSportsWithEvents: GetSportsWithEvents

    @MockK
    private lateinit var sportRepository: SportRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        getSportsWithEvents = GetSportsWithEvents(sportRepository)
    }

    @Test
    fun `invoke should return resource`() = runBlocking {
        val resource = Resource.Success(emptyList<Sport>())
        coEvery { sportRepository.getSports() } returns resource

        val result = getSportsWithEvents()

        assertThat(result).isEqualTo(resource)
    }
}