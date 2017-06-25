package com.monks.memento_mori.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.monks.memento_mori.R;
import com.monks.memento_mori.tools.Adapter;


/**
 * Created by monks on 18.04.2017.
 */

public class StatisticsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, null);
        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(new Adapter(getContext(), getChildFragmentManager()));
        return view;
    }
}
