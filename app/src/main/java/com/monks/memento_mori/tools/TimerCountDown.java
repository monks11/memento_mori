package com.monks.memento_mori.tools;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;
import com.monks.memento_mori.activity.MainActivity;
import com.monks.memento_mori.fragment.MainFragment;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.concurrent.TimeUnit;

/**
 * Created by monks on 12.04.2017.
 */

public class TimerCountDown {
    public CountDownTimer  countDownTimer;
    public CountDownTimer  countDownTimerHeartbeats;
    public long timeInSeconds = 10800;
    public long timeInSecondsHeartbeats = /*2177280*/2137280;
    public float pieEntry;
    public String hms = "123";
    public String hmsh = "321";

    private static String strange = "#,###";
    private static DecimalFormatSymbols custom = new DecimalFormatSymbols();
    private static DecimalFormat decimal_formatter = new DecimalFormat(strange, custom);

    private static TimerCountDown mInstance;

    public static void initInstance() {
        if (mInstance == null) {
            mInstance = new TimerCountDown();
        }
    }

    public static TimerCountDown getInstance() {
        return mInstance;
    }

    public long getTime() {
        return timeInSeconds;
    }

    public long getHeartbeats() {
        return timeInSecondsHeartbeats;
    }

    public void setTime(long var) {
        timeInSeconds = var;
    }

    public void setHeartbeats(long var) {
        timeInSecondsHeartbeats = var;
    }


    public void startTimer(long noOfMinutes) {
        noOfMinutes = noOfMinutes * 1000;
        countDownTimer = new CountDownTimer(noOfMinutes, 1000) {
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                //Convert milliseconds into hour,minute and seconds
               hms = String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(millis),
                        TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                        TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                timeInSeconds = ((TimeUnit.MILLISECONDS.toHours(millis)*3600)
                        +((TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)))*60)
                        + (TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))));
                //countdownTimerText.setText(hms);//set text
            }
            public void onFinish() {
                //countdownTimerText.setText("die!"); //On finish change timer text
                hms = "die!";
            }
        }.start();
    }

    public void startTimerHeartbeats(long noOfMinutes) {
        noOfMinutes = noOfMinutes * 1000;
        countDownTimerHeartbeats = new CountDownTimer(noOfMinutes, 1000) {
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                //Convert milliseconds into hour,minute and seconds
                custom.setGroupingSeparator(' ');
                decimal_formatter.setDecimalFormatSymbols(custom);
                decimal_formatter.setGroupingSize(3);
                hmsh = decimal_formatter.format((TimeUnit.MILLISECONDS.toHours(millis)*3600)
                        +((TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)))*60)
                        + (TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))));
                timeInSecondsHeartbeats = ((TimeUnit.MILLISECONDS.toHours(millis)*3600)
                        +((TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)))*60)
                        + (TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))));
                pieEntry = ((float)timeInSecondsHeartbeats/2177280)*100;
//                MainFragment.dataSet.removeFirst();
//                MainFragment.dataSet.removeLast();
//                MainFragment.dataSet.addEntry(new PieEntry(100-pieEntry));
//                MainFragment.dataSet.addEntry(new PieEntry(pieEntry));
//                chart.notifyDataSetChanged(); // let the chart know it's data changed
//                chart.invalidate();


                //countdownTimerText.setText(hmsh);//set text
            }
            public void onFinish() {
                //countdownTimerText.setText("WIN!"); //On finish change timer text
                hmsh = "WIN!";
            }
        }.start();
    }
}
