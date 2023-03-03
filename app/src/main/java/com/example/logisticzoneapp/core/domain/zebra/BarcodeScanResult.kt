package com.example.logisticzoneapp.core.domain.zebra

data class BarcodeScanResult(
    val barcodeContent: String = "",
    val symbology: String?
)
