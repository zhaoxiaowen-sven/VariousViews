package com.sven.variousviews.adapters.Listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by SVEN on 2017/9/17.
 */

public abstract class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{
    private View childView;
    private RecyclerView touchView;
    private GestureDetector mGestureDetector;

    public RecyclerTouchListener(Context context){
        mGestureDetector = new GestureDetector(context, new InternalGestureListener());
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        childView = rv.findChildViewUnder(e.getX(), e.getY());
        touchView = rv;
        mGestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public abstract void onItemClickListener(RecyclerView.ViewHolder viewHolder);
    public abstract void onItemLongClickListener(RecyclerView.ViewHolder viewHolder);

    class InternalGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            if (childView != null) {
                onItemClickListener(touchView.getChildViewHolder(childView));
            }
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            if (childView != null) {
                onItemLongClickListener(touchView.getChildViewHolder(childView));
            }
        }
    }
}
