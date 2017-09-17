package com.sven.variousviews.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sven.variousviews.R;
import com.sven.variousviews.adapters.Listener.RecyclerItemClickListener;
import com.sven.variousviews.bean.FruitBean;
import com.sven.variousviews.adapters.Listener.ItemTouchHelperAdapter;

import java.util.Collections;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements ItemTouchHelperAdapter{
    private static final String TAG = "RecyclerAdapter";
    private List<FruitBean> mFruitBeanList;
    private Context mContext;
    private RecyclerItemClickListener mItemListener;
    private boolean isLoadImage = true;

    public RecyclerAdapter(List<FruitBean> fruitBeanList, Context context) {
        this.mFruitBeanList = fruitBeanList;
        this.mContext = context;
    }

    public void setLoadImage(boolean loadImage) {
        isLoadImage = loadImage;
    }

    public void setItemListener(RecyclerItemClickListener itemListener) {
        this.mItemListener = itemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.i(TAG, "onBindViewHolder: ");
        FruitBean fruitBean = mFruitBeanList.get(position);
        Log.i(TAG, "onBindViewHolder: " + isLoadImage);
        if (isLoadImage) {
            Glide.with(mContext).load(fruitBean.getUrl()).into(holder.iv_pic);
        }
        holder.tv_id.setText(fruitBean.getId());
        holder.tv_name.setText(fruitBean.getName());
        holder.bt_download.setText("recycler");

        if (mItemListener != null) {
            Log.i(TAG, "onBindViewHolder: here");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onItemClickListener(holder.itemView, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mItemListener.onItemLongClickListener(holder.itemView, position);
                    return true;
                }
            });
        }

        holder.bt_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: bt_download");
                int pos = holder.getAdapterPosition();
                notifyItemRemoved(pos);
                mFruitBeanList.remove(pos);
                notifyItemRangeChanged(pos, getItemCount());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFruitBeanList.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //交换位置
        Collections.swap(mFruitBeanList,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        mFruitBeanList.remove(position);
        notifyItemRemoved(position);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_pic;
        TextView tv_id;
        TextView tv_name;
        Button bt_download;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_pic = (ImageView) itemView.findViewById(R.id.iv_pic);
            tv_id = (TextView) itemView.findViewById(R.id.tv_id);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            bt_download = (Button) itemView.findViewById(R.id.bt_download);
        }
    }
}
