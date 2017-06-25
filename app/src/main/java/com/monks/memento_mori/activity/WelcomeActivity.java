package com.monks.memento_mori.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.monks.memento_mori.R;
import com.monks.memento_mori.tools.AppSettings;
import com.monks.memento_mori.tools.SetTimeViewWithDelay;
import com.monks.memento_mori.tools.TimerCountDown;

import java.util.Timer;


/**
 * Created by monks on 11.04.2017.
 */

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView timeView;
    private AppSettings appSettings;
    TimerCountDown timerCountDown = TimerCountDown.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        timeView = (TextView) findViewById(R.id.timeView);

        appSettings = new AppSettings(this);
        timerCountDown.startTimer(appSettings.getTime());
        timerCountDown.startTimerHeartbeats(appSettings.getHeartbeats());
        //startService(new Intent(this, TimeHeartbeatsManagerService.class));
        Timer timer = new Timer();
        SetTimeViewWithDelay task = new SetTimeViewWithDelay(this, timeView, timerCountDown);
        long delay = 0;
        long period = 1000;
        timer.scheduleAtFixedRate(task,delay,period);
        Button btnGetDate = (Button) findViewById(R.id.buttonWelcomeActivity);
        btnGetDate.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.alfa_exit, R.anim.alfa_enter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonWelcomeActivity:
                appSettings.setTime(timerCountDown.timeInSeconds);
                setResult(RESULT_OK);
                finish();
                break;
        }
    }
}
