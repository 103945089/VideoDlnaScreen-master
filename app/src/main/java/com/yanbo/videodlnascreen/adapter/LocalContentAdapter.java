package com.yanbo.videodlnascreen.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yanbo.lib_screen.listener.ItemClickListener;
import com.yanbo.videodlnascreen.MainActivity;
import com.yanbo.videodlnascreen.R;

import org.fourthline.cling.support.model.DIDLObject;
import org.fourthline.cling.support.model.container.Container;
import org.fourthline.cling.support.model.item.Item;

import java.util.List;



/**
 * Created by lzan13 on 2018/3/19.
 */

public class LocalContentAdapter extends RecyclerView.Adapter<LocalContentAdapter.ContentHolder> {

    private LayoutInflater layoutInflater;
    private List<DIDLObject> objectList;
    private ItemClickListener clickListener;
    private Context context;
    public LocalContentAdapter(Context context, List<DIDLObject> list) {
        super();
        this.context=context;
        objectList = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ContentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_file_layout, parent, false);
        return new ContentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentHolder holder, final int position) {
        final DIDLObject object = objectList.get(position);
        if (object instanceof Container) {
            Container container = (Container) object;
            holder.nameView.setText(container.getTitle());
            holder.iconView.setImageResource(R.drawable.ic_folder_24dp);
        } else if (object instanceof Item) {
            Item item = (Item) object;
            if (item.getClazz().getValue().contains("movie")){
                holder.iconView.setImageResource(R.drawable.ic_movie3);
                holder.nameView.setText(item.getTitle()+".mp4");
                String s = new Gson().toJson(item);
                Log.e("fhp",""+s);
            }else if (item.getClazz().getValue().contains("music")){
                holder.iconView.setImageResource(R.drawable.ic_music);
                holder.nameView.setText(item.getTitle()+".mp3");
            }else {
                holder.iconView.setImageResource(R.drawable.ic_image);
                holder.nameView.setText(item.getTitle()+".jpg");
            }

        }
        holder.iconView.setVisibility(View.VISIBLE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onItemAction(position, object);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }


    public void setItemClickListener(ItemClickListener listener) {
        this.clickListener = listener;
    }

    static class ContentHolder extends RecyclerView.ViewHolder {
         TextView nameView;
         ImageView iconView,itemThumb;

        public ContentHolder(View itemView) {
            super(itemView);
            itemThumb= itemView.findViewById(R.id.itemThumb);
            nameView = itemView.findViewById(R.id.text_name);
            iconView = itemView.findViewById(R.id.img_icon);
        }
    }
}
