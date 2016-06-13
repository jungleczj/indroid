package com.telecom.indroid;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.telecom.indroid.model.Equip;
import com.telecom.indroid.model.QueryResult;
import com.telecom.indroid.webservice.WebService;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SearchActivity extends FragmentActivity{
        //implements NavigationView.OnNavigationItemSelectedListener{


    /*----------------搜索---------------*/
    public  static SearchView sv;
    private String query;
    private QueryResult queryResult=new QueryResult();
    private static final String RM_FIBER_INFO_BY_FAULT="RM_FIBER_INFO_BY_FAULT";
    private static final String RM_FIBER_ROUTE_BY_BM="RM_FIBER_ROUTE_BY_BM";
    private static final String RM_QX_LIST_BY_G3E="RM_QX_LIST_BY_G3E";
    private static final String RM_FIBER_FAULT_POSS="RM_FIBER_FAULT_POSS";

    /*----------------碎片---------------*/
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter = null;

    private ViewPager mPageVp = null;
    /**
     * Tab显示内容TextView
     */
    private TextView mTabFirstTv, mTabSecondTv,mTabThirdTv,mTabFourthTv = null;
    /**
     * Tab的那个引导线
     */
    private ImageView mTabLineIv = null;
    /**
     * Fragment
     */
    private FirstFragment mFirstFg = null;
    private SecondFragment mSecondFg = null;
    private ThirdFragment mThirdFg = null;
    private FourthFragment mFourthFg=null;

    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;
    /**
     * 屏幕的宽度
     */
    private int screenWidth;

    //列表适配器
    SimpleAdapter simpleAdapter;

    //菜单按钮
    private ImageButton mMenuBtn;
    private DrawerLayout slideMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        findById();
        init();
        initTabLineWidth();
        sv.setIconifiedByDefault(true);
        sv.setSubmitButtonEnabled(true);
        SpannableString spanText = new SpannableString("请输入关键字");
        // 设置字体大小
        spanText.setSpan(new AbsoluteSizeSpan(16, true), 0, spanText.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        // 设置字体颜色
        spanText.setSpan(new ForegroundColorSpan(Color.LTGRAY), 0,
                spanText.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sv.setQueryHint(spanText);
        int id = sv.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) sv.findViewById(id);
        textView.setTextColor(Color.WHITE);

        //通过反射，修改默认的样式，可以从android的search_view.xml中找到需要的组件
        try {
            Field field = sv.getClass().getDeclaredField("mSubmitButton");
            field.setAccessible(true);
            ImageView iv = (ImageView) field.get(sv);
            iv.setImageDrawable(this.getResources().getDrawable(R.drawable.search_btn));

        } catch (Exception e) {
            e.printStackTrace();
        }
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchActivity.this.query=query;
                switch (currentIndex){
                    case 0:
                        queryResult=WebService.query("",RM_FIBER_INFO_BY_FAULT,query);
                        break;
                    case 1:
                        queryResult=WebService.query("",RM_FIBER_ROUTE_BY_BM,query);
                        break;
                    case 2:
                        queryResult=WebService.query("",RM_QX_LIST_BY_G3E,query);
                        break;
                    case 3:
                        queryResult=WebService.query("",RM_FIBER_FAULT_POSS,query);
                        break;
                }
                //showResult();
                Toast.makeText(SearchActivity.this,query,Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

    }

    private void findById() {
        sv = (SearchView) findViewById(R.id.sv);

        mTabFirstTv = (TextView) this.findViewById(R.id.id_first_tv);
        mTabSecondTv = (TextView) this.findViewById(R.id.id_second_tv);
        mTabThirdTv=(TextView)this.findViewById(R.id.id_third_tv);
        mTabFourthTv=(TextView)this.findViewById(R.id.id_fourth_tv);
        mTabLineIv = (ImageView) this.findViewById(R.id.id_tab_line_iv);

        mPageVp = (ViewPager) this.findViewById(R.id.id_page_vp);

        mMenuBtn=(ImageButton)findViewById(R.id.menu);
        //slideMenu = (DrawerLayout) findViewById(R.id.slide_menu);
    }

    private void init() {
        mFirstFg = new FirstFragment();
        mSecondFg = new SecondFragment();
        mThirdFg=new ThirdFragment();
        mFourthFg=new FourthFragment();
        mFragmentList.add(mFirstFg);
        mFragmentList.add(mSecondFg);
        mFragmentList.add(mThirdFg);
        mFragmentList.add(mFourthFg);

        mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragmentList);
        mPageVp.setAdapter(mFragmentAdapter);
        mPageVp.setCurrentItem(0);

        mPageVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             * state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }

            /**
             * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
             * offsetPixels:当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float offset,
                                       int offsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                        .getLayoutParams();

                Log.e("offset:", offset + "");
                /**
                 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
                 * 设置mTabLineIv的左边距 滑动场景：
                 * 记3个页面,
                 * 从左到右分别为0,1,2
                 * 0->1; 1->2; 2->1; 1->0
                 */

                if (currentIndex == 0 && position == 0)// 0->1
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 4) + currentIndex
                            * (screenWidth / 4));
                } else if (currentIndex == 1 && position == 1)// 1->2
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 4) + currentIndex
                            * (screenWidth / 4));
                } else if (currentIndex == 2 && position == 2)// 2->3
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 4) + currentIndex
                            * (screenWidth / 4));
                } else if (currentIndex == 1 && position == 0) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 4) + currentIndex
                            * (screenWidth / 4));
                } else if (currentIndex == 2 && position == 1) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 4) + currentIndex
                            * (screenWidth / 4));
                } else if (currentIndex == 3 && position == 2) // 3->2
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 4) + currentIndex
                            * (screenWidth / 4));
                }
                mTabLineIv.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        mTabFirstTv.setTextColor(getResources().getColor(R.color.colorRed));
                        Toast.makeText(SearchActivity.this, "按受理单号（9xxx）或任务单号（2xxx）查询障碍光路信息", Toast.LENGTH_LONG).show();
                        sv.setQuery("", false);
                        //sv.setIconifiedByDefault(true);
                        break;
                    case 1:
                        mTabSecondTv.setTextColor(getResources().getColor(R.color.colorRed));
                        Toast.makeText(SearchActivity.this, "按光路编码查询光路全程路由", Toast.LENGTH_SHORT).show();
                        sv.setQuery("", false);
                        //sv.setIconifiedByDefault(true);
                        break;
                    case 2:
                        mTabThirdTv.setTextColor(getResources().getColor(R.color.colorRed));
                        Toast.makeText(SearchActivity.this, "按光路段ID查询纤芯清单", Toast.LENGTH_SHORT).show();
                        sv.setQuery("", false);
                        //sv.setIconifiedByDefault(true);
                        break;
                    case 3:
                        mTabFourthTv.setTextColor(getResources().getColor(R.color.colorRed));
                        Toast.makeText(SearchActivity.this, "按起止时间查询可能故障光缆,默认起止时间为当前时间", Toast.LENGTH_SHORT).show();
                        Date date = new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String time = formatter.format(date);
                        sv.setIconifiedByDefault(false);
                        sv.setQuery("[" + time + "][" + time + "]", false);
                        break;
                }
                currentIndex = position;
            }
        });

        mPageVp.setOnClickListener(new ViewPager.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

//        mMenuBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(SearchActivity.this,"menu",Toast.LENGTH_SHORT).show();
//                mMenuBtn.setBackgroundResource(R.color.colorPrimaryDark);
//
//                mMenuBtn.setBackgroundResource(R.color.colorPrimary);
//            }
//        });
        mMenuBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    Toast.makeText(SearchActivity.this,"menu",Toast.LENGTH_SHORT).show();
                    mMenuBtn.setBackgroundResource(R.color.colorPrimaryDark);

                }
                if(event.getAction()==MotionEvent.ACTION_UP){
                    mMenuBtn.setBackgroundResource(R.color.colorPrimary);
                }
                return false;
            }
        });

    }

    /**
     * 设置滑动条的宽度为屏幕的1/4(根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                .getLayoutParams();
        lp.width = screenWidth / 4;
        mTabLineIv.setLayoutParams(lp);
    }

    /**
     * 重置颜色
     */
    private void resetTextView() {
        mTabFirstTv.setTextColor(getResources().getColor(R.color.colorPrimary));
        mTabSecondTv.setTextColor(getResources().getColor(R.color.colorPrimary));
        mTabThirdTv.setTextColor(getResources().getColor(R.color.colorPrimary));
        mTabFourthTv.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    public void showResult() {
//        if (queryResult != null && queryResult.isSuccess()) {
            updateList();
//        } else {
//            if(queryResult==null)
//                System.out.println("test");
           // Toast.makeText(this, queryResult.getMessage(), Toast.LENGTH_SHORT).show();
//        }
    }

    private void updateList(){
        List<String> list=new ArrayList<>();
        List<Equip> equips=queryResult.getEquipsFromJsonResult();
        ArrayAdapter<String> arrayAdapter;
            switch (currentIndex){
                case 0:
                    for(Equip equip:equips)
                        list.add(String.format("光路编码:%s;\n业务名称:%s;\n障碍网元名称:%s;\n告警名称:%s;\n障碍定位信息:%s;\n最后告警时间|预处理信息:%s;\n",
                            equip.getId(),equip.getName(),equip.getInfo(),equip.getType(),equip.getPort(),equip.getAttr()));
                    arrayAdapter = new ArrayAdapter<String>(this,
                            android.R.layout.simple_list_item_1 ,list);
                    mFirstFg.getListView().setAdapter(arrayAdapter);
                    break;
                case 1:
                    for(Equip equip:equips)
                        list.add(String.format("光缆段ID:%s;\n光缆段名称:%s;\n纤芯号:%s;\n局向光纤:%s;\nODF位置:%s;\n承载业务数|告警业务数|采集时刻:%s;\n",
                            equip.getId(),equip.getName(),equip.getInfo(),equip.getType(),equip.getPort(),equip.getAttr()));
                    arrayAdapter = new ArrayAdapter<String>(this,
                            android.R.layout.simple_list_item_1 ,list);
                    mSecondFg.getListView().setAdapter(arrayAdapter);
                    break;
                case 2:
                    for(Equip equip:equips)
                        list.add(String.format("光路编码:%s;\n承载业务名称:%s;\n纤芯号:%s;\n业务告警时间（检测一天内，且当前仍未恢复的告警）:%s;\n告警名称:%s;\n影响用户数|预处理信息:%s;\n",
                            equip.getId(),equip.getName(),equip.getInfo(),equip.getType(),equip.getPort(),equip.getAttr()));
                    arrayAdapter = new ArrayAdapter<String>(this,
                            android.R.layout.simple_list_item_1 ,list);
                    mThirdFg.getListView().setAdapter(arrayAdapter);
                    break;
                case 3:
                    for(Equip equip:equips)
                        list.add(String.format("光缆段ID:%s;\n光缆段名称:%s;\n承载业务数:%s;\n告警业务数:%s;\n检测时间:%s;\n光缆等级:%s;\n",
                            equip.getId(),equip.getName(),equip.getInfo(),equip.getType(),equip.getPort(),equip.getAttr()));
                    arrayAdapter = new ArrayAdapter<String>(this,
                            android.R.layout.simple_list_item_1 ,list);
                    mFourthFg.getListView().setAdapter(arrayAdapter);
                    break;

            }
    }



//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        //getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camara) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

}
