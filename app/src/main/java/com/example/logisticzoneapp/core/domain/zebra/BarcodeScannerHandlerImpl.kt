package com.example.logisticzoneapp.core.domain.zebra

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BarcodeScannerHandlerImpl : IBarcodeScannerHandler {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun handleScanResult(barcodeScanResult: BarcodeScanResult): BarcodeScanState {
        val dateTime: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))
        return try {
            if(barcodeScanResult.symbology.isNullOrEmpty()){
                throw Exception("Type de codebarre nul ou introuvable")
            }
            BarcodeScanState(
                barcodeContent = (if(barcodeScanResult.barcodeContent == "LABEL-TYPE-DATAMATRIX"){
                    decodeDatamatrix(barcodeScanResult.barcodeContent)
                } else {
                    barcodeScanResult.barcodeContent as BarcodeType.DefaultBarcode
                }) as BarcodeType,
                symbology = barcodeScanResult.symbology,
                isLoading = false,
                dateTime = dateTime,
                errorMessage = null
            )
        } catch (e: Exception){
            BarcodeScanState(
                barcodeContent = if(barcodeScanResult.barcodeContent == "DATAMATRIX"){
                    decodeDatamatrix(barcodeScanResult.barcodeContent)
                } else {
                    (barcodeScanResult.barcodeContent) as BarcodeType.DefaultBarcode
                },
                symbology = barcodeScanResult.symbology,
                isLoading = false,
                dateTime = dateTime,
                errorMessage = null
            )
        }
    }

    // Using 'fold' to accumulate the field values into a 'Datamatrix' object.
    // Each token is processed in turn and the appropriate field is updated using 'copy'
    // if a token doesn't match any prefixes, it returns the current 'decoded' object unchanged
    override fun decodeDatamatrix(barcodeContent: String): BarcodeType.Datamatrix =
        barcodeContent.split("\u001e", "\u001d").fold(BarcodeType.Datamatrix()){
            decoded, token ->
            when {
                token.startsWith("V") -> decoded.copy(frenchNumber = token.drop(1))
                token.startsWith("S") -> decoded.copy(serialNumber = token.drop(1))
                token.startsWith("P") -> decoded.copy(reference = token.drop(1))
                token.startsWith("Q") -> decoded.copy(quantity = token.drop(1))
                token.startsWith("H") -> decoded.copy(batchNumber = token.drop(1))
                token.startsWith("5D") -> decoded.copy(productionDate = token.drop(2))
                else -> decoded
            }
        }

// First version
//    override fun decodeDatamatrix(barcodeContent: String): BarcodeType.Datamatrix {
//        val splitDatamatrix = barcodeContent.split("\u001e", "\u001d")
//        val decodedDatamatrix = BarcodeType.Datamatrix("", "", "", "", "", "")
//        for(token in splitDatamatrix){
//            if(token.startsWith("V")) decodedDatamatrix.frenchNumber = token.drop(1)
//            if(token.startsWith("S")) decodedDatamatrix.serialNumber = token.drop(1)
//            if(token.startsWith("P")) decodedDatamatrix.reference = token.drop(1)
//            if(token.startsWith("Q")) decodedDatamatrix.quantity = token.drop(1)
//            if(token.startsWith("H")) decodedDatamatrix.batchNumber = token.drop(1)
//            if(token.startsWith("5D")) decodedDatamatrix.productionDate = token.drop(2)
//        }
//        return decodedDatamatrix
//    }
}