package com.dgkatoudis.kaizengamingassignment.presentation.sports

import com.dgkatoudis.kaizengamingassignment.domain.model.SportEvent
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class DomainToUiSportEventsMapperTest {

    private lateinit var domainToUiSportEventsMapper: DomainToUiSportEventsMapper

    @Before
    fun setup() {
        domainToUiSportEventsMapper = DomainToUiSportEventsMapper(dateFormatter = DateFormatter())
    }

    @Test
    fun `getTeams should return 2 teams`() {
        val result = domainToUiSportEventsMapper.map(
            listOf(
                SportEvent(
                    id = "",
                    name = "a-b",
                    sportId = "",
                    epoch = 1
                )
            )
        )
        assertThat(result[0].team1).isEqualTo("a")
        assertThat(result[0].team2).isEqualTo("b")

    }

    @Test
    fun `getTeams should return 1 team and empty 2nd when no -`() {
        val result = domainToUiSportEventsMapper.map(
            listOf(
                SportEvent(
                    id = "",
                    name = "ab",
                    sportId = "",
                    epoch = 1
                )
            )
        )

        assertThat(result[0].team2).isEmpty()
    }
}