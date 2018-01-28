package com.sven.variousviews.activities;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.sven.variousviews.R;
import com.sven.variousviews.adapters.VpAdapter;
import com.sven.variousviews.adapters.VpFragmentAdapter;
import com.sven.variousviews.fragments.Fragment1;
import com.sven.variousviews.fragments.Fragment2;
import com.sven.variousviews.fragments.Fragment3;

import java.util.ArrayList;
import java.util.List;

public class VpActivity extends FragmentActivity implements
        ViewPager.OnPageChangeListener, TabLayout.OnTabSelectedListener {

    private static final String TAG = "VpActivity";
    private ViewPager mViewPager;
    private View view1, view2, view3;
    private List<View> pageList;
    private List<Fragment> fragments = new ArrayList<>();
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp);
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        //注册监听
        mViewPager.addOnPageChangeListener(this);
        mTabLayout.addOnTabSelectedListener(this);
        initView();
//        initFragments();
    }

    private void initFragments() {
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        mViewPager.setAdapter(new VpFragmentAdapter(getSupportFragmentManager(), fragments));
    }

    private void initView() {

        LayoutInflater layoutInflater = getLayoutInflater();
        view1 = layoutInflater.inflate(R.layout.page1, null);
        view2 = layoutInflater.inflate(R.layout.page2, null);
        view3 = layoutInflater.inflate(R.layout.page3, null);

        pageList = new ArrayList<View>();
        pageList.add(view1);
        pageList.add(view2);
        pageList.add(view3);

        Log.i(TAG, "initView: " + pageList.size());
        mViewPager.setAdapter(new VpAdapter(pageList));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mTabLayout.getTabAt(position).select();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
