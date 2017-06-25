package com.monks.memento_mori.tools;

import android.app.Activity;
import android.widget.TextView;

import com.github.mikephil.charting.data.PieEntry;
import com.monks.memento_mori.fragment.MainFragment;

import java.util.TimerTask;

import static com.monks.memento_mori.fragment.MainFragment.chart;

/**
 * Created by monks on 25.04.2017.
 */

public class SetHeartbeatsViewWithDelay extends TimerTask {



    Activity activity;
    TextView heartbeatsView;
    TimerCountDown timer;
    public SetHeartbeatsViewWithDelay(Activity activity, TextView heartbeatsView, TimerCountDown timer)
    {
        this.activity = activity;
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
                heartbeatsView.setText(timer.hmsh);
                MainFragment.dataSet.removeFirst();
                MainFragment.dataSet.removeLast();
                MainFragment.dataSet.addEntry(new PieEntry(100-timer.pieEntry));
                MainFragment.dataSet.addEntry(new PieEntry(timer.pieEntry));
                chart.notifyDataSetChanged(); // let the chart know it's data changed
                chart.invalidate();
            }
        });
    }
}
