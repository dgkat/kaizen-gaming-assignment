package com.dgkatoudis.kaizengamingassignment.presentation.sports

import com.dgkatoudis.kaizengamingassignment.domain.model.SportEvent
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class DomainToUiSportEventsMapperTest {

    private lateinit var domainToUiSportEventsMapper: DomainToUiSportEventsMapper

    @Before
    fun setup() {
        domainToUiSportEventsMapper = DomainToUiSportEventsMapper()
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
        assertThat(result).isEqualTo(
            listOf(
                UiSportEvent(
                    id = "",
                    team1 = "a",
                    team2 = "b",
                    sportId = "",
                    date = 1
                )
            )
        )
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