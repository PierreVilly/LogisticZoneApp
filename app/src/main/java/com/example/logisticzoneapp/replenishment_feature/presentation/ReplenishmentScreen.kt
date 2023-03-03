package com.example.logisticzoneapp.replenishment_feature.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.logisticzoneapp.core.presentation.SystemBroadcastReceiver

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ReplenishmentScreen(
    replenishmentViewModel: ReplenishmentViewModel = hiltViewModel()
){
    if(Build.MANUFACTURER == "Zebra Technologies"){
        SystemBroadcastReceiver(systemAction = "com.example.zebradatawedgebroadcastintent.SCAN"){
                receiverState ->
            val action = receiverState?.action ?: return@SystemBroadcastReceiver
            if(action == "com.example.zebradatawedgebroadcastintent.SCAN") {
                // TODO : handle Barcode Scan
                replenishmentViewModel.handleBarcodeScan()
            }
        }
    }
}