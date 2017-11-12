package com.sven.variousviews.adapters;


import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class VpAdapter extends PagerAdapter {
    private List<View> mPageList;

    public VpAdapter(List<View> pageList) {
        this.mPageList = pageList;
    }

    @Override
    public int getCount() {
//        Log.i("VpActivity", "getCount: "+mPageList.size());
        return mPageList == null ? 0 : mPageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
//        return false;
//        Log.i("VpActivity", "isViewFromObject: "+mPageList.size());
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 将当前位置的View移除
//        Log.i("VpActivity", "destroyItem: "+position);
        container.removeView(mPageList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        return super.instantiateItem(container, position);
//        Log.i("VpActivity", "instantiateItem: "+mPageList.size());
        container.addView(mPageList.get(position));
        return mPageList.get(position);
    }
}
