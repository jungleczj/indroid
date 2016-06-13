package com.telecom.indroid;

import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.telecom.indroid.model.Equip;

import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link idroid.telecom.com.idroid.FirstFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link idroid.telecom.com.idroid.FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends ListFragment {
    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        listView = (ListView)view.findViewById(android.R.id.list);
        return view;
    }
}
