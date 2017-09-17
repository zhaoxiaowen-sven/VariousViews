package com.sven.variousviews.activities;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.GridView;

import com.sven.variousviews.R;
import com.sven.variousviews.Utils;
import com.sven.variousviews.adapters.ListAdapter;

public class GridActivity extends Activity {

    private GridView mGridView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        mGridView = (GridView) findViewById(R.id.grid_view);
        mGridView.setAdapter(new ListAdapter(this, Utils.initData()));
    }
}
