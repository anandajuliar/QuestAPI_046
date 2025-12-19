package com.example.questapi_046.uicontroller.route

import com.example.questapi_046.R

object DestinasiDetail : DestinasiNavigasi {
    override val route = "item_detail"
    override val titleRes = R.string.detail_siswa
    const val SISWA_ID = "itemId"
    val routeWithArgs = "$route/{$SISWA_ID}"
}