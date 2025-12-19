package com.example.questapi_046.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questapi_046.modeldata.DetailSiswa
import com.example.questapi_046.modeldata.toDetailSiswa
import com.example.questapi_046.repositori.RepositoryDataSiswa
import kotlinx.coroutines.launch

sealed interface DetailUiState {
    data class Success(val detailSiswa: DetailSiswa) : DetailUiState
    object Error : DetailUiState
    object Loading : DetailUiState
}

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDataSiswa: RepositoryDataSiswa
) : ViewModel() {

    private val _id: Int = checkNotNull(savedStateHandle["id"])

    var detailUiState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    init {
        getDetailSiswa()
    }

    fun getDetailSiswa() {
        viewModelScope.launch {
            detailUiState = DetailUiState.Loading
            detailUiState = try {
                val dataSiswa = repositoryDataSiswa.getDataSiswa().find { it.id == _id }
                if (dataSiswa != null) {
                    DetailUiState.Success(dataSiswa.toDetailSiswa())
                } else {
                    DetailUiState.Error
                }
            } catch (e: Exception) {
                DetailUiState.Error
            }
        }
    }

    suspend fun deleteSiswa() {
        try {
            // repositoryDataSiswa.deleteSiswa(_id)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}