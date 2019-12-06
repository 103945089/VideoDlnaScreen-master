package com.yanbo.videodlnascreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL;

/**
 * NeverMore
 * 2019/12/6
 **/
public class VideoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<EntityVideo> datas;
    public VideoAdapter(Context context, ArrayList<EntityVideo> datas){
        this.context=context;
        this.datas=datas;
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.video_item,null);
        }
        ImageView itemImg = convertView.findViewById(R.id.itemImg);
        TextView itemName = convertView.findViewById(R.id.itemName);

        Glide.with(context).load(datas.get(position).getThumbPath()).into(itemImg);
        itemName.setText(datas.get(position).getPath());

        return convertView;
    }
}
