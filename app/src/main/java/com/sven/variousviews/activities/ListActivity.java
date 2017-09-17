package com.sven.variousviews.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.sven.variousviews.R;
import com.sven.variousviews.Utils;
import com.sven.variousviews.adapters.ListAdapter;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity2";
    private ListView listView;
    private View headerView;
    private View footerView;
    private View footerButton;
    private Button headerButton;

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
        headerView = LayoutInflater.from(this).inflate(R.layout.list_header_view, null);
        footerView = LayoutInflater.from(this).inflate(R.layout.list_footer_view, null);
        headerButton = (Button) headerView.findViewById(R.id.header_bt);
        headerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: header_bt");
                Toast.makeText(ListActivity.this, "headerButton clicked" , Toast.LENGTH_SHORT).show();

            }
        });
        footerButton = footerView.findViewById(R.id.footer_bt);
        footerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: footer_bt");
                Toast.makeText(ListActivity.this, "footerButton clicked", Toast.LENGTH_SHORT).show();


            }
        });
        listView.addHeaderView(headerView, null, false);
        listView.addFooterView(footerView);

        listView.setAdapter(adapter);


    }
}
