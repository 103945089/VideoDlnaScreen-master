package com.yanbo.videodlnascreen;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.yanbo.lib_screen.event.DIDLEvent;
import com.yanbo.lib_screen.listener.ItemClickListener;
import com.yanbo.lib_screen.manager.ClingManager;
import com.yanbo.videodlnascreen.adapter.LocalContentAdapter;

import org.fourthline.cling.support.model.DIDLObject;
import org.fourthline.cling.support.model.container.Container;
import org.fourthline.cling.support.model.item.Item;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * NeverMore
 * 2019/12/6
 **/
public class VideoAty extends AppCompatActivity {

    private RecyclerView listView;
    //设置本地投屏的信息
    private List<DIDLObject> objectList=new ArrayList<>();
    private LocalContentAdapter localContentAdapter;
    private View btBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_video);
        EventBus.getDefault().register(this);
        btBack = findViewById(R.id.btBack);
        listView = ((RecyclerView) findViewById(R.id.listView));
        localContentAdapter=new LocalContentAdapter(this,objectList);
        listView.setAdapter(localContentAdapter);
        listView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL,false));


//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                EntityVideo entityVideo = datas.get(position);
//                ClingManager.getInstance().setLocalItem(new Item());
//            }
//        });
        ClingManager.getInstance().searchLocalContent("0");

        localContentAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemAction(int action, Object object) {
                if (object instanceof Container) {
                    Container container = (Container) object;
                    searchLocalContent(container.getId());
                } else if (object instanceof Item) {
                    Item item = (Item) object;
                    ClingManager.getInstance().setLocalItem(item);
                    Intent intent = new Intent(VideoAty.this, MediaPlayActivity.class);

                    startActivity(intent);
                }
            }
        });

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClingManager.getInstance().searchLocalContent("0");

            }
        });
    }

    /**
     *
     * @param containerId
     */
    private void searchLocalContent(String containerId) {
        ClingManager.getInstance().searchLocalContent(containerId);
    }
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void getit(DIDLEvent event){
        Log.e("fhp","-----");
        objectList.clear();
        if (event.content.getContainers().size() > 0) {
            objectList.addAll(event.content.getContainers());
        } else if (event.content.getItems().size() > 0) {
            objectList.addAll(event.content.getItems());
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                localContentAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
