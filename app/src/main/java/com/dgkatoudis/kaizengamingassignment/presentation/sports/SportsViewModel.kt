package com.dgkatoudis.kaizengamingassignment.presentation.sports

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgkatoudis.kaizengamingassignment.domain.model.Resource
import com.dgkatoudis.kaizengamingassignment.domain.usecases.GetSportsWithEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SportsViewModel @Inject constructor(
    private val getSportsWithEvents: GetSportsWithEvents,
    private val sportsMapper: SportsMapper
) : ViewModel() {

    var sports by mutableStateOf(SportsUiState())
        private set

    init {
        viewModelScope.launch {
            sports = when (val result = getSportsWithEvents()) {
                is Resource.Success -> sports.copy(
                    data = sportsMapper.map(
                        result.data ?: emptyList()
                    )
                )
                is Resource.Error -> TODO()
            }
        }
    }
}