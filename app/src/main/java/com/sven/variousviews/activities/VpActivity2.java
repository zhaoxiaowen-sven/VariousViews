package com.sven.variousviews.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sven.variousviews.R;
import com.sven.variousviews.fragments.Fragment1;
import com.sven.variousviews.fragments.Fragment2;
import com.sven.variousviews.fragments.Fragment3;

import java.util.ArrayList;
import java.util.List;

public class VpActivity2 extends FragmentActivity {

    private static final String TAG = "VpActivity2";
    private ViewPager mViewPager;
    private PagerTabStrip mPagerTabStrip;
    private View view1, view2, view3;
    private TabLayout mTabLayout;
    List<String> titleList = new ArrayList<>();
    List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getSupportActionBar().hide();//隐藏掉整个ActionBar
        setContentView(R.layout.activity_vp2);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout2);
        mViewPager = (ViewPager) findViewById(R.id.vp2);
        initTitleList();
//        mPagerTabStrip = (PagerTabStrip) findViewById(R.id.vp_strip);
//        bindViews();
        bindFragments();
    }

    // 实现方式2: viewPager + fragment
    private void bindFragments(){
        initFragments();
        mViewPager.setAdapter(new FragPagerAdapter(getSupportFragmentManager(), fragmentList));
        for (int i = 0; i < titleList.size(); i++){
            mTabLayout.addTab(mTabLayout.newTab().setText(titleList.get(i)));
        }
//        mTabLayout.setupWithViewPager(mViewPager);
// ???
      mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }

    private void initFragments() {
        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment3());
    }

    private void initTitleList(){
        titleList.add("title1");
        titleList.add("title2");
        titleList.add("title3");
    }

    class FragPagerAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> mFragmentList;
        public FragPagerAdapter(FragmentManager fm , List<Fragment> fragmentList){
            super(fm);
            this.mFragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

//        @Override
//        public CharSequence getPageTitle(int position) {
//            return titleList.get(position);
//        }
    }

    // 实现方式1：viewPager + views
    private void bindViews() {
        LayoutInflater layoutInflater = getLayoutInflater();
        view1 = layoutInflater.inflate(R.layout.page1, null);
        view2 = layoutInflater.inflate(R.layout.page2, null);
        view3 = layoutInflater.inflate(R.layout.page3, null);

        List<View> viewList = new ArrayList<>();
        viewList = new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        mViewPager.setAdapter(new MyPagerAdapter(viewList, titleList));
//        mPagerTabStrip.setTabIndicatorColor(Color.RED);
    }

    /* viewpager + view*/
    class MyPagerAdapter extends PagerAdapter {
        private List<View> mViewList;
        private List<String> mTitleList;
        public MyPagerAdapter(List<View> viewList, List<String> titleList) {
            this.mViewList = viewList;
            this.mTitleList = titleList;
        }

        @Override
        public int getCount() {
            return mViewList == null ? 0 : mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);
        }
    }
}
