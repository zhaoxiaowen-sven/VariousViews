package com.sven.variousviews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.sven.variousviews.activities.GridActivity;
import com.sven.variousviews.activities.ListActivity;
import com.sven.variousviews.activities.RecyclerActivity;
import com.sven.variousviews.activities.VpActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_0:
                startActivity(new Intent(this, ListActivity.class));
                break;
            case R.id.bt_1:
                startActivity(new Intent(this, RecyclerActivity.class));
                break;
            case R.id.bt_2:
                startActivity(new Intent(this, GridActivity.class));
                break;
            case R.id.bt_3:

                break;
            case R.id.bt_4:
                startActivity(new Intent(this, VpActivity.class));
                break;
            default:
                break;
        }
    }
}
