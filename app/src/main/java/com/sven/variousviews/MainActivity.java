package com.sven.variousviews;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.sven.variousviews.activities.GridActivity;
import com.sven.variousviews.activities.ListActivity;
import com.sven.variousviews.activities.RecyclerActivity;
import com.sven.variousviews.activities.VpActivity;
import com.sven.variousviews.activities.WebActivity;

public class MainActivity extends AppCompatActivity {
    private ImageView animationIV;
    private AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animationIV = (ImageView) findViewById(R.id.animationIV);
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
                animationIV.setImageResource(R.drawable.anim);
                animationDrawable = (AnimationDrawable) animationIV.getDrawable();
                animationDrawable.start();
                break;
            case R.id.bt_4:
                startActivity(new Intent(this, VpActivity.class));
                break;
            case R.id.bt_5:
                startActivity(new Intent(this, WebActivity.class));
                break;
            default:
                break;
        }
    }
}
