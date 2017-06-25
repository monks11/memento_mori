package com.monks.memento_mori.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.monks.memento_mori.R;
import com.monks.memento_mori.fragment.GetTimeFragment;
import com.monks.memento_mori.fragment.InfoFragment;
import com.monks.memento_mori.fragment.MainFragment;
import com.monks.memento_mori.fragment.ShareTimeFragment;
import com.monks.memento_mori.fragment.StatisticsFragment;
import com.monks.memento_mori.service.TimeHeartbeatsManagerService;
import com.monks.memento_mori.tools.AppSettings;
import com.monks.memento_mori.tools.SetTimeViewWithDelay;
import com.monks.memento_mori.tools.TimerCountDown;

import java.util.Timer;

public class MainActivity extends AppCompatActivity implements MainFragment.OnSelectedButtonListener  {
    private static  final int REQUEST_NAME = 101;
    private static  final int SHARE_TIME = 1;
    private static  final int GET_TIME = 2;
    private static  final int STATISTICS = 3;
    private static  final int INFO = 4;
    private static  final int HISTORY = 5;

    AppSettings appSettings;
    public  static TextView timeView;

    FragmentManager manager = getSupportFragmentManager();
    ShareTimeFragment shareTimeFragment = new ShareTimeFragment();
    GetTimeFragment getTimeFragment = new GetTimeFragment();
    StatisticsFragment statisticsFragment = new StatisticsFragment();
    InfoFragment infoFragment = new InfoFragment();
    MainFragment mainFragment = new MainFragment();
    SetTimeViewWithDelay task;
    Timer timer;
    long delay = 0;
    long period = 1000;

    TimerCountDown timerCountDown = TimerCountDown.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appSettings = new AppSettings(this);
        timeView = (TextView)findViewById(R.id.timeViewMainActivity);

        if(appSettings.ifFirstRun()){
            startService(new Intent(this, TimeHeartbeatsManagerService.class));
            Intent intent = new Intent(this, FirstRunNameActivity.class);
            startActivityForResult(intent, REQUEST_NAME);
        }else{
            startService(new Intent(this, TimeHeartbeatsManagerService.class));
        }
        timer = new Timer();
        task = new SetTimeViewWithDelay(this, timeView, timerCountDown);
        timer.scheduleAtFixedRate(task,delay,period);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment, mainFragment);
        transaction.commit();
    }

    @Override
    protected void onResume() {
        timer = new Timer();
        task = new SetTimeViewWithDelay(this, timeView, timerCountDown);
        timer.scheduleAtFixedRate(task,delay,period);
        timerCountDown.startTimer(appSettings.getTime());
        timerCountDown.startTimerHeartbeats(appSettings.getHeartbeats());
        super.onResume();
    }

    @Override
    protected void onPause() {
        timer.cancel();
        //appSettings.setTime(timerCountDown.timeInSeconds);
        //appSettings.setHeartbeats(timerCountDown.timeInSecondsHeartbeats);
        timerCountDown.countDownTimer.cancel();
        timerCountDown.countDownTimerHeartbeats.cancel();
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_NAME && resultCode == RESULT_CANCELED){
            this.finish();
        }
        if(requestCode == REQUEST_NAME && resultCode == RESULT_OK){
            appSettings.setShowTerms(false);
        }
    }

    @Override
    public void onButtonSelected(int buttonIndex) {
        Intent intent;
        switch (buttonIndex) {
            case SHARE_TIME:
                changeFragment(shareTimeFragment);
                break;
            case GET_TIME:
                changeFragment(getTimeFragment);
                break;
            case STATISTICS:
                changeFragment(statisticsFragment);
                break;
            case INFO:
                changeFragment(infoFragment);
                break;
            case HISTORY:
                intent = new Intent(this, HistoryActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.alfa_exit, R.anim.alfa_enter);
                break;
        }
    }

    private void changeFragment(Fragment fragment){
        FragmentTransaction transaction;
        transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.alfa_exit, R.anim.alfa_enter,
                R.anim.alfa_exit, R.anim.alfa_enter);
        //transaction.hide(mainFragment);
        transaction.replace(R.id.fragment, fragment).addToBackStack(null);
        transaction.commit();
    }
}