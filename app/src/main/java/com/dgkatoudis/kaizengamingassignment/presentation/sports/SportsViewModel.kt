package com.dgkatoudis.kaizengamingassignment.presentation.sports

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgkatoudis.kaizengamingassignment.domain.model.Resource
import com.dgkatoudis.kaizengamingassignment.domain.usecases.GetSportsWithEvents
import com.dgkatoudis.kaizengamingassignment.util.SportsRowState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportsViewModel @Inject constructor(
    private val getSportsWithEvents: GetSportsWithEvents,
    private val sportsMapper: SportsMapper,
    private val sportsEventMapper: SportsEventMapper
) : ViewModel() {

    var sports by mutableStateOf(SportsUiState())
        private set
    var sportsList = mutableStateListOf<UiSport>()
        private set

    var list = mutableListOf<UiSportEvent>()
        private set

    var eventsList = mutableStateListOf<MutableList<UiSportEvent>>()
        private set

    init {
        viewModelScope.launch {
            when (val result = getSportsWithEvents()) {
                is Resource.Success -> {

                    sportsList.addAll(sportsMapper.map(result.data ?: emptyList()))

                    for (i in sportsList.indices) {
                        eventsList.add(
                            sportsList[i].events as MutableList<UiSportEvent>
                        )
                    }

                    println("eventsList $eventsList")
                }
                is Resource.Error -> {
                    TODO()
                }
            }
            /*sports = when (val result = getSportsWithEvents()) {
                is Resource.Success -> {

                    sports.copy(
                        data = sportsMapper.map(
                            result.data ?: emptyList()
                        )
                    )
                }
                is Resource.Error -> TODO()
            }*/

        }
    }

    fun setFavorite(sportIndex: Int, sportEventIndex: Int) {
        // eventsList[0][0]=eventsList[0][0].copy(isFavorite = true)

        val newList = sportsList[sportIndex].events.toMutableList()

        val tempItem = newList[sportEventIndex].copy(isFavorite = true)

        newList.removeAt(sportEventIndex)
        newList.add(0,tempItem)
        sportsList[sportIndex] = sportsList[sportIndex].copy(
            events = newList
        )

    }

    fun setExpanded(index: Int) {
        val expanded = if (sportsList[index].expanded == SportsRowState.Expanded) {
            SportsRowState.Collapsed
        } else {
            SportsRowState.Expanded
        }
        sportsList[index] = sportsList[index].copy(expanded = expanded)
    }
}