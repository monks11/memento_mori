package com.monks.memento_mori.tools;

import android.app.Activity;
import android.widget.TextView;

import java.util.TimerTask;

/**
 * Created by monks on 25.04.2017.
 */

public class SetTimeAndHeartbeatsViewsWithDelay extends TimerTask {

    Activity activity;
    TextView timeView;
    TextView heartbeatsView;
    TimerCountDown timer;

    public SetTimeAndHeartbeatsViewsWithDelay(Activity activity, TextView timeView, TextView heartbeatsView, TimerCountDown timer)
    {
        this.activity = activity;
        this.timeView = timeView;
        this.heartbeatsView = heartbeatsView;
        this.timer = timer;
    }
    @Override
    public void run()
    {
        activity.runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                timeView.setText(timer.hms);
                heartbeatsView.setText(timer.hmsh);
            }
        });
    }
}
