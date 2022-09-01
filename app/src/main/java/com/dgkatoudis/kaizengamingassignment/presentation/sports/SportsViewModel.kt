package com.dgkatoudis.kaizengamingassignment.presentation.sports

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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
    private val domainToUiSportsMapper: DomainToUiSportsMapper
) : ViewModel() {

    val uiState = mutableStateOf(SportsUiState.LOADING)

    var sportsList = mutableStateListOf<UiSport>()
        private set


    init {
        viewModelScope.launch {
            when (val result = getSportsWithEvents()) {
                is Resource.Success -> {
                    uiState.value = SportsUiState.SUCCESS
                    sportsList.addAll(domainToUiSportsMapper.map(result.data ?: emptyList()))
                }
                is Resource.Error -> {
                    uiState.value = SportsUiState.ERROR
                }
                is Resource.Loading -> {
                    uiState.value = SportsUiState.LOADING
                }
            }
        }
    }

    fun setFavorite(sportIndex: Int, sportEventIndex: Int) {

        val newList = sportsList[sportIndex].events.toMutableList()

        val tempItem = newList[sportEventIndex].copy(isFavorite = true)

        newList.removeAt(sportEventIndex)
        newList.add(0, tempItem)
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