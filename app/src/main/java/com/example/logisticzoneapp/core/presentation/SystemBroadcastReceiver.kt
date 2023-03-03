package com.example.logisticzoneapp.core.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import com.example.logisticzoneapp.core.domain.zebra.DatawedgeIntentActions

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun SystemBroadcastReceiver(
    systemAction: String,
    onSystemEvent:(intent: Intent?)->Unit
){
    val context = LocalContext.current
    val currentOnSystemEvent by rememberUpdatedState(onSystemEvent)

    DisposableEffect(context, systemAction){
        val intentFilter = IntentFilter()
        intentFilter.addAction(systemAction)
        intentFilter.addCategory(DatawedgeIntentActions.DATAWEDGE_RETURN_CATEGORY)
        val broadcast = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                currentOnSystemEvent(intent)
            }
        }

        val listenToBroadcastsFromOtherApps = true
        val receiverFlags = if (listenToBroadcastsFromOtherApps) {
            ComponentActivity.RECEIVER_EXPORTED
        } else {
            ComponentActivity.RECEIVER_NOT_EXPORTED
        }
        context.registerReceiver(broadcast, intentFilter, receiverFlags)

        onDispose {
            context.unregisterReceiver(broadcast)
        }
    }
}