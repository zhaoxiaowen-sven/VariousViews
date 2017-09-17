package com.sven.variousviews.adapters;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sven.variousviews.R;
import com.sven.variousviews.bean.FruitBean;

import java.util.List;


public class ListAdapter extends BaseAdapter {

    private static final String TAG = "ListAdapter";
    private Context mContext;
    private List<FruitBean> mFruitBeanList;


    public ListAdapter(Context context, List<FruitBean> list) {
        this.mContext = context;
        this.mFruitBeanList = list;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mFruitBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return mFruitBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.fruit_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_id = (TextView) convertView.findViewById(R.id.tv_id);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.iv_pic = (ImageView) convertView.findViewById(R.id.iv_pic);
            viewHolder.bt_download = (Button) convertView.findViewById(R.id.bt_download);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final FruitBean fruitBean = mFruitBeanList.get(position);
        if (fruitBean != null) {
            Glide.with(mContext).load(fruitBean.getUrl()).into(viewHolder.iv_pic);
            viewHolder.tv_id.setText(fruitBean.getId());
            viewHolder.tv_name.setText(fruitBean.getName());
            viewHolder.bt_download.setText("delete");
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "onClick: convertView clicked" + position);
                }
            });
            
            viewHolder.bt_download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "onClick: bt_download");
//                    Toast.makeText(mContext, "onclick bt_down", Toast.LENGTH_SHORT).show();
                    mFruitBeanList.remove(position);
//                    mFruitBeanList.add(fruitBean);
                    notifyDataSetChanged();
                }
            });

            viewHolder.tv_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "onclick tv_name " + fruitBean.getId(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        return convertView;
    }

    static class ViewHolder {
        public ImageView iv_pic;
        public TextView tv_id;
        public TextView tv_name;
        public Button bt_download;
    }
}
