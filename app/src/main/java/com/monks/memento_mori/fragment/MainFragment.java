package com.monks.memento_mori.fragment;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.monks.memento_mori.R;
import com.monks.memento_mori.tools.AppSettings;
import com.monks.memento_mori.tools.SetHeartbeatsViewWithDelay;
import com.monks.memento_mori.tools.TimerCountDown;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * Created by monks on 25.04.2017.
 */

public class MainFragment extends Fragment implements View.OnClickListener {

    AppSettings appSettings;
    public static TextView heartbeatsView;
    public  static PieChart chart;
    public  static PieDataSet dataSet;
    TimerCountDown ms = TimerCountDown.getInstance();
    SetHeartbeatsViewWithDelay task;
    Timer timer;
    long delay = 0;
    long period = 1000;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        appSettings = new AppSettings(getActivity());
        heartbeatsView = (TextView)view.findViewById(R.id.heartbeatsViewMainActivity);

        chart = (PieChart)view.findViewById(R.id.pieChartMainActivity);
        List<PieEntry> pieEntries = new ArrayList<>();
        float a = ((float)appSettings.getHeartbeats()/2177280)*100;

        float heartbeatsToWin[] = {100-a, a};
        for(int i = 0; i < heartbeatsToWin.length; ++i){
            pieEntries.add(new PieEntry(heartbeatsToWin[i]));
        }
        dataSet = new PieDataSet(pieEntries,null);



        //timer.scheduleAtFixedRate(task,delay,period);

        ImageButton imageViewHeart = (ImageButton)view.findViewById(R.id.buttonImageViewHeart);
        imageViewHeart.setBackgroundResource(R.drawable.heart_animation);
        imageViewHeart.setOnClickListener(this);
        Button btnShareTime = (Button)view.findViewById(R.id.buttonShareTimeMainActivity);
        btnShareTime.setOnClickListener(this);
        Button btnGetTime = (Button)view.findViewById(R.id.buttonGetTimeMainActivity);
        btnGetTime.setOnClickListener(this);
        Button btnStatistics = (Button)view.findViewById(R.id.buttonStatisticsMainActivity);
        btnStatistics.setOnClickListener(this);
        Button btnInfo = (Button)view.findViewById(R.id.buttonInfoMainActivity);
        btnInfo.setOnClickListener(this);

        setupPieChart(chart);
        AnimationDrawable mAnimationDrawable = (AnimationDrawable) imageViewHeart.getBackground();
        mAnimationDrawable.start();
        return view;
    }

    @Override
    public void onResume() {
        timer = new Timer();
        task = new SetHeartbeatsViewWithDelay(this.getActivity(), heartbeatsView, ms);
        timer.scheduleAtFixedRate(task,delay,period);
        super.onResume();
    }

    @Override
    public void onPause() {
        timer.cancel();
        super.onPause();
    }

    public static void setupPieChart(PieChart chart) {
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

    int translateIdToIndex(int id) {
        int index = -1;
        switch (id) {
            case R.id.buttonShareTimeMainActivity:
                index = 1;
                break;
            case R.id.buttonGetTimeMainActivity:
                index = 2;
                break;
            case R.id.buttonStatisticsMainActivity:
                index = 3;
                break;
            case R.id.buttonInfoMainActivity:
                index = 4;
                break;
            case R.id.buttonImageViewHeart:
                index = 5;
                break;
        }
        return index;
    }

    @Override
    public void onClick(View v) {
        int buttonIndex = translateIdToIndex(v.getId());
        OnSelectedButtonListener listener = (OnSelectedButtonListener) getActivity();
        listener.onButtonSelected(buttonIndex);

    }

    public interface OnSelectedButtonListener {
        void onButtonSelected(int buttonIndex);
    }
}
