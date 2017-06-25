package com.monks.memento_mori.recivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.monks.memento_mori.service.TimeHeartbeatsManagerService;

public class OnLoadBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent updateIntent = new Intent(context, TimeHeartbeatsManagerService.class);
        context.startService(updateIntent);
    }
}
