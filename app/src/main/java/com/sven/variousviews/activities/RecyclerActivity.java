package com.sven.variousviews.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.sven.variousviews.R;
import com.sven.variousviews.Utils;
import com.sven.variousviews.adapters.Listener.RecyclerTouchListener;
import com.sven.variousviews.adapters.Listener.SimpleItemTouchHelperCallback;
import com.sven.variousviews.adapters.RecyclerAdapter;

public class RecyclerActivity extends AppCompatActivity {

    private static final String TAG = "RecyclerActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        final RecyclerAdapter adapter = new RecyclerAdapter(Utils.initData(), this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

//        1.按键监听方式1
//        adapter.setItemListener(new RecyclerAdapter.RecyclerItemClickListener() {
//            @Override
//            public void onItemClickListener(View view, int position) {
//                Log.i(TAG, "onItemClickListener: RecyclerItemClickListener");
//            }
//
//            @Override
//            public void onItemLongClickListener(View view, int position) {
//                Log.i(TAG, "onItemLongClickListener: RecyclerItemClickListener");
//            }
//        });

//      2.item监听方式2
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this) {
            @Override
            public void onItemClickListener(RecyclerView.ViewHolder viewHolder) {
                Log.i(TAG, "addOnItemTouchListener: onItemClickListener");
            }

            @Override
            public void onItemLongClickListener(RecyclerView.ViewHolder viewHolder) {
                Log.i(TAG, "addOnItemTouchListener: onItemLongClickListener");
            }
        });

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                Log.i(TAG, "onScrollStateChanged: " + newState);
//                switch (newState) {
//                    case SCROLL_STATE_IDLE:
//                        adapter.setLoadImage(true);
//                        break;
//                    case SCROLL_STATE_TOUCH_SCROLL:
//                    case SCROLL_STATE_FLING:
//                        adapter.setLoadImage(false);
//                        break;
//                }
//            }
//        });
        // 事件监听2
//        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });
        recyclerView.setAdapter(adapter);

//        设置滑动删除
        //先实例化Callback
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        //用Callback构造ItemtouchHelper
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        touchHelper.attachToRecyclerView(recyclerView);

    }
}

