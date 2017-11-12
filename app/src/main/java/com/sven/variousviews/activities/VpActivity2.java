package com.sven.variousviews.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sven.variousviews.R;

import java.util.ArrayList;
import java.util.List;

public class VpActivity2 extends FragmentActivity {

    private static final String TAG = "VpActivity2";
    private ViewPager mViewPager;
    private PagerTabStrip mPagerTabStrip;
    private View view1, view2, view3;
    private List<View> viewList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp2);
        mViewPager = (ViewPager) findViewById(R.id.vp2);
//        mPagerTabStrip = (PagerTabStrip) findViewById(R.id.vp_strip);
        bindViews();
    }

    private void bindViews() {
        LayoutInflater layoutInflater = getLayoutInflater();
        view1 = layoutInflater.inflate(R.layout.page1, null);
        view2 = layoutInflater.inflate(R.layout.page2, null);
        view3 = layoutInflater.inflate(R.layout.page3, null);

        viewList = new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        titleList.add("title1");
        titleList.add("title2");
        titleList.add("title3");

        mViewPager.setAdapter(new MyPagerAdapter(viewList, titleList));
//        mPagerTabStrip.setTabIndicatorColor(Color.RED);
//        mPagerTabStrip.
    }

    class MyPagerAdapter extends PagerAdapter {
        private List<View> mViewList;
        private List<String> mTitleList;
        public MyPagerAdapter(List<View> viewList, List<String> titleList) {
            this.mViewList = viewList;
            this.mTitleList = titleList;
        }

        @Override
        public int getCount() {
//            notifyDataSetChanged();
            return mViewList == null ? 0 : mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            return super.instantiateItem(container, position);
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            return super.getPageTitle(position);
            return mTitleList.get(position);
        }
    }
}
