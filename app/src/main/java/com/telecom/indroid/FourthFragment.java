package com.telecom.indroid;

import android.app.DatePickerDialog;
import android.content.Context;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.telecom.indroid.model.Equip;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2016/6/10.
 */
public class FourthFragment extends ListFragment {
    private String starttime;
    private String endtime;
    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fourth, container, false);
        listView = (ListView)view.findViewById(android.R.id.list);
        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date=new Date();
                String format="yyyy-MM-dd";
                SimpleDateFormat dateFormat=new SimpleDateFormat(format);
                String cur=dateFormat.format(date);
                FourthFragment.this.starttime=cur;
                FourthFragment.this.endtime=cur;
                System.out.println(cur);
                int curYear = Integer.parseInt(cur.split("-")[0]);
                int curMonth=Integer.parseInt(cur.split("-")[1]);
                int curDay=Integer.parseInt(cur.split("-")[2]);
                DatePickerDialog startdate = new DatePickerDialog(FourthFragment.this.getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        String y=String.valueOf(year);
                        String month=String.valueOf(monthOfYear+1);
                        String day=String.valueOf(dayOfMonth);
                        if(monthOfYear+1<10){
                            month="0"+month;
                        }
                        if(dayOfMonth+1<10){
                            day="0"+day;
                        }
                        FourthFragment.this.starttime=String.format("[%s-%s-%S]",y,month,day);
                        //System.out.println(FourthFragment.this.starttime);
                        Toast.makeText(FourthFragment.this.getActivity(), year + "year " + (monthOfYear+1 ) + "month " + dayOfMonth + "day", Toast.LENGTH_SHORT).show();
                    }

                },curYear , curMonth-1, curDay);
                startdate.setTitle("请选择开始时间");

                DatePickerDialog enddate = new DatePickerDialog(FourthFragment.this.getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                                  int dayOfMonth) {
                                String y=String.valueOf(year);
                                String month=String.valueOf(monthOfYear+1);
                                String day=String.valueOf(dayOfMonth);
                                if(monthOfYear+1<10){
                                    month="0"+month;
                                }
                                if(dayOfMonth+1<10){
                                    day="0"+day;
                                }
                                FourthFragment.this.endtime=String.format("[%s-%s-%S]",y,month,day);
                                //System.out.println(FourthFragment.this.endtime);
                                Toast.makeText(FourthFragment.this.getActivity(), year + "year " + (monthOfYear+1 ) + "month " + dayOfMonth + "day", Toast.LENGTH_SHORT).show();
                                SearchActivity.sv.setQuery(starttime + endtime, false);
                                //SearchActivity.
                            }
                        },curYear , curMonth-1, curDay);
                enddate.setTitle("请选择结束时间");
                enddate.show();
                startdate.show();
            }
        });
        return view;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    private List<Map<String,Equip>> getData(){
        List<Map<String, Equip>> list = new ArrayList<>();
        Map<String, Equip> map=new HashMap<>();
//        List<Equip> equips=queryResult.getEquipsFromJsonResult();
//        for(Equip e:equips){
//            map.put(e.getId(),e);
//            list.add(map);
//        }
        Equip e=new Equip();
        e.setAttr("attr\n");
        e.setId("sdgaksdgh\nsa\nd gaisd\ngha\ndughaidsu\nghakdsgak\n");

        map.put("test",e);
        list.add(map);
        return list;
    }
}
