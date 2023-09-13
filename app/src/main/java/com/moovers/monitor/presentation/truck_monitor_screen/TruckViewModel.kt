package com.moovers.monitor.presentation.truck_monitor_screen

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moovers.monitor.ExceptionParser

import com.moovers.monitor.data.networking.CoroutineDispatcherProvider
import com.moovers.monitor.domain.model.TruckResponseItem
import com.moovers.monitor.domain.usecase.GetTruckUseCase
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TruckViewModel @Inject constructor(
    private val getTruckUseCase: GetTruckUseCase,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : ViewModel() {

    private val _uiState = MutableStateFlow<TruckUiState>(TruckUiState.Loading)
    val uiState: StateFlow<TruckUiState> = _uiState

    init{
        getTruckList()
    }




    fun getTruckList() {
        _uiState.value = TruckUiState.Loading

        viewModelScope.launch(coroutineDispatcherProvider.IO()) {
            try {
                val result = getTruckUseCase.execute()
                val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
                result.sortWith(compareByDescending<TruckResponseItem> { dateFormat.parse(it.lastUpdated) })

                _uiState.value = TruckUiState.Loaded(result.toMutableList())
            } catch (error: Exception) {
                _uiState.value = TruckUiState.Error(ExceptionParser.getMessage(error))
            }
        }
    }



    sealed class TruckUiState {
        object Loading : TruckUiState()
        class Loaded(val truckResponse: MutableList<TruckResponseItem>) : TruckUiState()
        class Error(@StringRes val message: Int) : TruckUiState()
    }
}