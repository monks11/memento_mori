package com.monks.memento_mori.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.monks.memento_mori.R;
import com.monks.memento_mori.tools.AppSettings;

/**
 * Created by 1 on 05.06.2017.
 */

public class PageFragment extends Fragment {
    private int pageNumber;
    AppSettings appSettings;
    public static PageFragment newInstance(int page) {
        PageFragment fragment = new PageFragment();
        Bundle args=new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appSettings = new AppSettings(getContext());
        pageNumber = getArguments() != null ? getArguments().getInt("num") : 1;
    }
    public static String getTitle(Context context, int position) {
        if(position == 0) {
            return "Today";
        }else if(position == 1){
            return "Week";
        }else {
            return "All time";
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        TextView pageHeader = (TextView)view.findViewById(R.id.displayText);
        TextView countUnlock = (TextView)view.findViewById(R.id.countUnlock);
        TextView statisticTime = (TextView)view.findViewById(R.id.statisticTime);
        pageHeader.setText("Фрагмент " + String.valueOf(pageNumber+1));
        long time, hour, minute, second;
       if(pageNumber == 0) {
           time = appSettings.getStatisticTimeToday();
           hour = time/3600;
           minute = (time - hour*3600) / 60;
           second = (time - ((hour*3600) + (minute*60)));
           countUnlock.setText("Device activity: " + "\n"
                   +  appSettings.getCountUnlockToday() + " unlocking");
           statisticTime.setText("Device operating time: " + "\n"
                   + hour + "h. " + minute + "min. " + second + "sec.");
       }else if(pageNumber == 1) {
           time = appSettings.getStatisticTimeWeek();
           hour = time/3600;
           minute = (time - hour*3600) / 60;
           second = (time - ((hour*3600) + (minute*60)));
           countUnlock.setText("Device activity: " + "\n"
                   +  appSettings.getCountUnlockWeek() + " unlocking");
           statisticTime.setText("Device operating time: " + "\n"
                   + hour + "h. " + minute + "min. " + second + "sec.");
       }else{
           time = appSettings.getStatisticTimeAllTime();
           hour = time/3600;
           minute = (time - hour*3600) / 60;
           second = (time - ((hour*3600) + (minute*60)));
           countUnlock.setText("Device activity: " + "\n"
                   +  appSettings.getCountUnlockAllTime() + " unlocking");
           statisticTime.setText("Device operating time: " + "\n"
                   + hour + "h. " + minute + "min. " + second + "sec.");
        }

        return view;
    }

}
