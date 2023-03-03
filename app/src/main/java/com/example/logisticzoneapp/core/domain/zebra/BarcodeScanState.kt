package com.example.logisticzoneapp.core.domain.zebra

data class BarcodeScanState(
    val barcodeContent: BarcodeType,
    val symbology: String?,
    val dateTime: String = "",
    val errorMessage: String?,
    val isLoading: Boolean = false
)

sealed interface BarcodeType {
    fun getBarcodeVule(): String
    data class Datamatrix(
        val frenchNumber: String = "",
        val serialNumber: String = "",
        val reference: String = "",
        val quantity: String = "",
        val batchNumber: String = "",
        val productionDate: String = ""
    ): BarcodeType {
        override fun getBarcodeVule(): String {
            TODO("Not yet implemented")
        }
    }

    data class DefaultBarcode(
        val content: String
    ): BarcodeType {
        override fun getBarcodeVule(): String {
            return content
        }
    }
}