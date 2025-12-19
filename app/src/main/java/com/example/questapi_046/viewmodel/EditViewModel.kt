package com.example.questapi_046.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questapi_046.modeldata.DetailSiswa
import com.example.questapi_046.modeldata.UIStateSiswa
import com.example.questapi_046.modeldata.toDataSiswa
import com.example.questapi_046.modeldata.toUiStateSiswa
import com.example.questapi_046.repositori.RepositoryDataSiswa
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDataSiswa: RepositoryDataSiswa
) : ViewModel() {

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    // Mengambil ID dari navigasi (DestinasiDetail.SISWA_ID)
    private val _id: Int = checkNotNull(savedStateHandle["id"])

    init {
        viewModelScope.launch {
            // Mengambil data siswa berdasarkan ID untuk ditampilkan di form edit
            val dataSiswa = repositoryDataSiswa.getDataSiswa().find { it.id == _id }
            if (dataSiswa != null) {
                uiStateSiswa = dataSiswa.toUiStateSiswa(true)
            }
        }
    }

    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa = UIStateSiswa(
            detailSiswa = detailSiswa,
            isEntryValid = validasiInput(detailSiswa)
        )
    }

    suspend fun updateSiswa() {
        if (validasiInput()) {
            try {
                // Logika update (biasanya menggunakan PUT/UPDATE di repositori)
                // repositoryDataSiswa.updateSiswa(_id, uiStateSiswa.detailSiswa.toDataSiswa())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}