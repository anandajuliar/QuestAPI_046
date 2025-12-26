package com.example.questapi_046.repositori


import com.example.questapi_046.apiservice.ServiceApiSiswa
import com.example.questapi_046.modeldata.DataSiswa
import retrofit2.Response

interface RepositoryDataSiswa {
    suspend fun getDataSiswa(): List<DataSiswa>
    suspend fun postDataSiswa(dataSiswa: DataSiswa): retrofit2.Response<Void>
    suspend fun getSatuSiswa(id: Int): DataSiswa
    suspend fun editSatuSiswa(id: Int, dataSiswa: DataSiswa):retrofit2.Response<Void>
    suspend fun hapusSatuSiswa(id: Int):retrofit2.Response<Void>
}

class JaringanRepositoryDataSiswa(
    private val serviceApiSiswa: ServiceApiSiswa
) : RepositoryDataSiswa {

    override suspend fun getDataSiswa(): List<DataSiswa> {
        return serviceApiSiswa.getSiswa()
    }

    override suspend fun postDataSiswa(dataSiswa: DataSiswa): Response<Void> {
        return serviceApiSiswa.postSiswa(dataSiswa)
    }
}