package com.monks.memento_mori;

import android.app.Application;

import com.monks.memento_mori.tools.TimerCountDown;
import com.monks.memento_mori.tools.TypefaceUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by monks on 22.04.2017.
 */

public class StartApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TimerCountDown.initInstance();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/font.ttf");
    }
}
