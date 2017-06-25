package com.monks.memento_mori.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.monks.memento_mori.R;
import com.monks.memento_mori.tools.AppSettings;

import java.util.Calendar;

/**
 * Created by monks on 11.04.2017.
 */


public class FirstRunBirthdayActivity extends AppCompatActivity implements View.OnClickListener
{
    DatePicker dp;
    AppSettings appSettings;
    private static final int REQUEST_WELCOME = 103;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_run_birthday);

        //Calendar c= Calendar.getInstance();
        dp = (DatePicker) findViewById(R.id.date_picker);
        Button btnGetDate = (Button) findViewById(R.id.buttonGetBirthdayActivity);
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
            case R.id.buttonGetBirthdayActivity:
                appSettings = new AppSettings(this);
                appSettings.setBirthday( dp.getYear(), dp.getMonth(), dp.getDayOfMonth());
                Intent intent = new Intent(this, WelcomeActivity.class);
                startActivityForResult(intent, REQUEST_WELCOME);
                break;
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_WELCOME && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }else{
            setResult(RESULT_CANCELED);
            finish();
        }
    }
}