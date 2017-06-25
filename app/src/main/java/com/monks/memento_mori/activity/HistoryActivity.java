package com.monks.memento_mori.activity;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.monks.memento_mori.R;
import com.monks.memento_mori.tools.AppSettings;
import com.monks.memento_mori.tools.SetTimeAndHeartbeatsViewsWithDelay;
import com.monks.memento_mori.tools.TimerCountDown;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * Created by monks on 21.04.2017.
 */

public class HistoryActivity extends AppCompatActivity {
    AppSettings appSettings;
    TextView timeView;
    TextView timeViewHearbeats;
    TimerCountDown timerCountDown = TimerCountDown.getInstance();
    SetTimeAndHeartbeatsViewsWithDelay task;
    Timer timer;
    long delay = 0;
    long period = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        appSettings = new AppSettings(this);
        timeView = (TextView) findViewById(R.id.timeViewHistoryActivity);
        timeViewHearbeats = (TextView) findViewById(R.id.heartbeatsViewHistoryActivity);
        ImageView imageViewHeart = (ImageView) findViewById(R.id.imageViewHeartHistoryActivity);
        imageViewHeart.setBackgroundResource(R.drawable.heart_animation);
        setupPieChart();
        AnimationDrawable mAnimationDrawable = (AnimationDrawable) imageViewHeart.getBackground();
        mAnimationDrawable.start();
    }

    @Override
    protected void onResume() {
        timer = new Timer();
        task = new SetTimeAndHeartbeatsViewsWithDelay(this, timeView, timeViewHearbeats, timerCountDown);
        timer.scheduleAtFixedRate(task,delay,period);
        timerCountDown.startTimer(appSettings.getTime());
        timerCountDown.startTimerHeartbeats(appSettings.getHeartbeats());
        super.onResume();
    }

    @Override
    protected void onPause() {
        timer.cancel();
        appSettings.setTime(timerCountDown.timeInSeconds);
        appSettings.setHeartbeats(timerCountDown.timeInSecondsHeartbeats);
        timerCountDown.countDownTimer.cancel();
        timerCountDown.countDownTimerHeartbeats.cancel();
        overridePendingTransition(R.anim.alfa_exit, R.anim.alfa_enter);
        super.onPause();
    }

    public void setupPieChart() {
        PieChart chart;
        PieDataSet dataSet;
        chart = (PieChart)findViewById(R.id.pieChartHistoryActivity);
        List<PieEntry> pieEntries = new ArrayList<>();
        float a = ((float)appSettings.getHeartbeats()/2177280)*100;

        float heartbeatsToWin[] = {100-a, a};
        for(int i = 0; i < heartbeatsToWin.length; ++i){
            pieEntries.add(new PieEntry(heartbeatsToWin[i]));
        }
        dataSet = new PieDataSet(pieEntries,null);
        dataSet.setColors(Color.GRAY,Color.BLACK);
        dataSet.setDrawValues(false);
        PieData data = new PieData(dataSet);
        chart.setData(data);
        chart.setTouchEnabled(false);
        chart.setDescription(null);
        chart.setUsePercentValues(false);
        Legend legend = chart.getLegend();
        legend.setEnabled(false);
        chart.invalidate();
    }
}
