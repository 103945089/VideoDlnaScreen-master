package com.yanbo.videodlnascreen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.yanbo.lib_screen.entity.RemoteItem;
import com.yanbo.lib_screen.manager.ClingManager;

public class MainActivity extends AppCompatActivity {
    Button button;
    Button button1,button2;
    RecyclerView recyclerView;
    String  url1="ftp://ygdy8:ygdy8@yg68.dydytt.net:7036/%E9%98%B3%E5%85%89%E7%94%B5%E5%BD%B1www.ygdy8.com.%E7%AC%AC%E4%B8%80%E6%BB%B4%E8%A1%805%EF%BC%9A%E6%9C%80%E5%90%8E%E7%9A%84%E8%A1%80.BD.1080p.%E4%B8%AD%E8%8B%B1%E5%8F%8C%E5%AD%97%E5%B9%95.mkv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        recyclerView=findViewById(R.id.recycler_view);
        ClingManager.getInstance().startClingService();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeviceListActivity.startSelf(MainActivity.this);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoteItem itemurl1 = new RemoteItem("一路之下", "425703", "张杰",
                        107362668, "00:04:33", "1280x720", url1);
                ClingManager.getInstance().setRemoteItem(itemurl1);
                MediaPlayActivity.startSelf(MainActivity.this);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VideoAty.class);
                startActivity(intent);
            }
        });
    }

    public  static Bitmap getVideoThumb(String path) {

        MediaMetadataRetriever media = new MediaMetadataRetriever();

        media.setDataSource(path);

        return  media.getFrameAtTime();

    }
}
