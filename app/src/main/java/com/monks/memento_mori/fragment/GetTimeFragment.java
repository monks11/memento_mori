package com.monks.memento_mori.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.monks.memento_mori.R;
import com.monks.memento_mori.tools.SetTimeViewWithDelay;
import com.monks.memento_mori.tools.TimerCountDown;

import java.util.Timer;


/**
 * Created by monks on 18.04.2017.
 */

public class GetTimeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_get_time, null);
    }
}
