package com.sven.variousviews.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sven.variousviews.R;

import java.util.ArrayList;
import java.util.List;

public class VpActivity2 extends FragmentActivity {

    private static final String TAG = "VpActivity2";
    private int currentPos = 0;
    private ViewPager mViewPager;
    private Context mContext;
    List<ViewHolder> items = new ArrayList<>();
    private Handler mHandler;
    private boolean stopAutoScroll = false;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (!stopAutoScroll) {
                mViewPager.setCurrentItem(currentPos + 1, true);
                mHandler.postDelayed(mRunnable, 1000);
            }
        }
    };

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
//        getSupportActionBar().hide();//隐藏掉整个ActionBar
        setContentView(R.layout.activity_vp2);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                mViewPager.setCurrentItem(currentPos + 1, true);
                sendEmptyMessageDelayed(0, 1000);
            }
        };
        mViewPager = (ViewPager) findViewById(R.id.vp2);
//        mPagerTabStrip = (PagerTabStrip) findViewById(R.id.vp_strip);
        bindViews();
//        bindFragments();

    }

    // 实现方式1：viewPager + views
    @SuppressLint("ClickableViewAccessibility")
    private void bindViews() {

        items = new ArrayList<>();
        items.add(new ViewHolder("4", R.drawable.hzw3));
        items.add(new ViewHolder("1", R.drawable.hzw1));
        items.add(new ViewHolder("2", R.drawable.hzw2));
        items.add(new ViewHolder("3", R.drawable.hzw3));
        items.add(new ViewHolder("1", R.drawable.hzw4));
        mViewPager.setAdapter(new MyPagerAdapter(items));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentPos = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                if (state != ViewPager.SCROLL_STATE_IDLE) return;

                // 当视图在第一个时，将页面号设置为图片的最后一张。
                if (currentPos == 0) {
                    mViewPager.setCurrentItem(items.size() - 2, false);

                } else if (currentPos == items.size() - 1) {
                    // 当视图在最后一个是,将页面号设置为图片的第一张。
                    mViewPager.setCurrentItem(1, false);
                }
            }
        });

        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        stopAutoScroll = true;
                        mHandler.removeCallbacks(mRunnable);
                        break;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        stopAutoScroll = false;
                        mHandler.postDelayed(mRunnable, 4000);
                        break;

                }
                return false;
            }
        });
//        mViewPager.setCurrentItem(100 * items.size());
//        mHandler.sendEmptyMessageDelayed(0, 500);
//        int firstPage = Integer.MAX_VALUE / 2 / items.size() * items.size();
        mViewPager.setCurrentItem(1, false);
        mViewPager.postDelayed(mRunnable, 1000);
    }


    /* viewpager + view*/
    class MyPagerAdapter extends PagerAdapter {
        private List<ViewHolder> mItemList;

        public MyPagerAdapter(List<ViewHolder> viewList) {
            this.mItemList = viewList;
        }

        @Override
        public int getCount() {
            return mItemList == null ? 0 : mItemList.size();
//            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            View view = mViewList.get(position);
//            View view = mViewList.get(innerSize(position));
//            ViewParent vp = view.getParent();
//            if (vp != null) {
//                ViewGroup parent = (ViewGroup) vp;
//                parent.removeView(view);
//            }
            View view = View.inflate(mContext, R.layout.vp_item, null);
            ImageView iv = (ImageView) view.findViewById(R.id.vp_img);
            iv.setImageResource(mItemList.get(position).resId);
            TextView tv = (TextView) view.findViewById(R.id.vp_tv);
            tv.setText(mItemList.get(position).title);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
//            container.removeView(mItemList.get(position));
//            container.removeView(mViewList.get(innerSize(position)));
        }

//        @Override
//        public CharSequence getPageTitle(int position) {
//            return mTitleList.get(position);
//        }

//        private int innerSize(int position) {
//            return position % mViewList.size();
//        }
    }

    class ViewHolder {
        String title;
        int resId;

        public ViewHolder(String title, int resId) {
            this.title = title;
            this.resId = resId;
        }
    }
}
