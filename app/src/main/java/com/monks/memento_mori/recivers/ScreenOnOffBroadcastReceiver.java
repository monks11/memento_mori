package com.monks.memento_mori.recivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.monks.memento_mori.service.TimeHeartbeatsManagerService;
import com.monks.memento_mori.tools.AppSettings;

public class ScreenOnOffBroadcastReceiver extends BroadcastReceiver {
    AppSettings appSettings;
    private long currentTimeOn;
    public long currentTimeOff;
    long dissHeart;
    long dissTime;
    long dissTimeStatistic;
    long statisticTimeToday;
    long statisticTimeAllTime;
    int countUnlockToday;
    int countUnlockAllTime;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("monksjk","onReceive Screen");
        appSettings = new AppSettings(context);
        if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
            Log.d("monksjk","onReceive -> ACTION_SCREEN_ON");
            currentTimeOn = System.currentTimeMillis() / 1000;
            dissHeart = appSettings.getHeartbeats() - (currentTimeOn - currentTimeOff);
            if(currentTimeOff != 0){
                appSettings.setHeartbeats(dissHeart);
                countUnlockToday = appSettings.getCountUnlockToday();
                countUnlockAllTime = appSettings.getCountUnlockAllTime();

                appSettings.setCountUnlockToday(countUnlockToday + 1);
                appSettings.setCountUnlockWeek(1);
                appSettings.setCountUnlockAllTime(countUnlockAllTime + 1);
            }
        }
        else if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
            Log.d("monksjk","onReceive -> ACTION_SCREEN_OFF");
            currentTimeOff = System.currentTimeMillis() / 1000;
            dissTime = appSettings.getTime() - (currentTimeOff - currentTimeOn);
            dissTimeStatistic = currentTimeOff - currentTimeOn;
            if(dissTime > 0){
                appSettings.setTime(dissTime);
                statisticTimeToday = appSettings.getStatisticTimeToday();
                statisticTimeAllTime = appSettings.getStatisticTimeAllTime();

                appSettings.setStatisticTimeToday(statisticTimeToday + dissTimeStatistic);
                appSettings.setStatisticTimeWeek(dissTimeStatistic);
                appSettings.setStatisticTimeAllTime(statisticTimeAllTime + dissTimeStatistic);
            }
            dissHeart = appSettings.getHeartbeats() - (currentTimeOff - currentTimeOn);
            if(dissHeart > 0){
                appSettings.setHeartbeats(dissHeart);
            }
        }
    }
}
