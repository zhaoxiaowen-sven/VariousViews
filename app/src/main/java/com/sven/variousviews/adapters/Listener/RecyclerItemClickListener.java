package com.sven.variousviews.adapters.Listener;

import android.view.View;

public interface RecyclerItemClickListener {

    void onItemClickListener(View view, int position);
    void onItemLongClickListener(View view, int position);
}