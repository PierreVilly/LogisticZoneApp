package com.example.logisticzoneapp.core.domain.zebra

data class BarcodeScanState(
    val barcodeContent: BarcodeType? = null,
    val symbology: String? = null,
    val dateTime: String = "",
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)

sealed interface BarcodeType {
    fun getBarcodeValue(): String
    data class Datamatrix(
        val frenchNumber: String = "",
        val serialNumber: String = "",
        val reference: String = "",
        val quantity: String = "",
        val batchNumber: String = "",
        val productionDate: String = "",
    ): BarcodeType {
        override fun getBarcodeValue(): String {
            return "$frenchNumber!$serialNumber!$reference!$quantity!$batchNumber!$productionDate!"
        }
    }

    data class DefaultBarcode(
        val content: String
    ): BarcodeType {
        override fun getBarcodeValue(): String {
            return content
        }
    }
}