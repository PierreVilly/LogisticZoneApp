package com.example.logisticzoneapp.core.domain.zebra

interface IBarcodeScannerHandler {
    fun handleScanResult(barcodeScanResult: BarcodeScanResult): BarcodeScanState

    fun decodeDatamatrix(barcodeContent: String) : BarcodeType.Datamatrix
}