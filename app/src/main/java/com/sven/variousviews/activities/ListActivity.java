package com.sven.variousviews.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.sven.variousviews.R;
import com.sven.variousviews.Utils;
import com.sven.variousviews.adapters.ListAdapter;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity2";
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.list_view);
        ListAdapter adapter = new ListAdapter(ListActivity.this, Utils.initData());
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.i(TAG, "onItemClick: " + position);
//                Toast.makeText(ListActivity.this, "onItemClick: " + position, Toast.LENGTH_SHORT).show();
//            }
//        });
        listView.setAdapter(adapter);
    }
}
