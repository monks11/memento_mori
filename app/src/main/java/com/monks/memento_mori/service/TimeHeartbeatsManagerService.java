package com.monks.memento_mori.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import com.monks.memento_mori.recivers.ScreenOnOffBroadcastReceiver;

public class TimeHeartbeatsManagerService extends Service {

    private ScreenOnOffBroadcastReceiver mScreenStateReceiver = new ScreenOnOffBroadcastReceiver();

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d("monksjk","onCreate");
        IntentFilter screenStateFilter = new IntentFilter();
        screenStateFilter.addAction(Intent.ACTION_SCREEN_ON);
        screenStateFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mScreenStateReceiver, screenStateFilter);
        mScreenStateReceiver.currentTimeOff = System.currentTimeMillis() / 1000;
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("monksjk","onStartCommand");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        Log.d("monksjk","onDestroyService");
        unregisterReceiver(mScreenStateReceiver);
        super.onDestroy();
    }
}
