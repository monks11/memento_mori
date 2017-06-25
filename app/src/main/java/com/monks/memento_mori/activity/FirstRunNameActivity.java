package com.monks.memento_mori.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.monks.memento_mori.R;
import com.monks.memento_mori.tools.AppSettings;

/**
 * Created by monks on 11.04.2017.
 */

public class FirstRunNameActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextName;
    private static final int REQUEST_BIRTHDAY = 102;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_run_name);

        editTextName = (EditText) findViewById(R.id.editTextGetNameFirstRunActivity);
        Button button = (Button) findViewById(R.id.buttonGetNameFirstRunActivity);
        button.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.alfa_exit, R.anim.alfa_enter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonGetNameFirstRunActivity:
                if (!("".equals(editTextName.getText().toString()))) {
                    AppSettings appSettings = new AppSettings(this);
                    appSettings.setName(editTextName.getText().toString());
                    Intent intent = new Intent(this, FirstRunBirthdayActivity.class);
                    startActivityForResult(intent, REQUEST_BIRTHDAY);
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "say me your name", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            default:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_BIRTHDAY && resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }else{
            setResult(RESULT_CANCELED);
            finish();
        }
    }
}