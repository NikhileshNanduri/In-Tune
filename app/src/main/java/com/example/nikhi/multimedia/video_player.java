package com.example.nikhi.multimedia;

import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

public class video_player extends AppCompatActivity {

    VideoView videoView;
    ListView listView;
    ArrayList<String> videoList;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        videoView = (VideoView) findViewById(R.id.videoView);
        listView = (ListView) findViewById(R.id.videoList);

        videoList = new ArrayList<>();
        videoList.add("THOR ENTRY AT WAKANDA");
        videoList.add("THANOS VS HULK");





        adapter = new ArrayAdapter(this , android.R.layout.simple_list_item_1 , videoList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Toast.makeText(getApplicationContext() , position + "" , Toast.LENGTH_SHORT).show();*/
                switch (position)
                {
                    case 0 :
                            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.thorentry));
                            break;


                    case 1 :
                            videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName()+ "/" + R.raw.hulkvsthanos));
                            break;

                    default:
                            break;
                }

                videoView.setMediaController(new MediaController(video_player.this));
                videoView.requestFocus();
                videoView.start();
            }
        });

    }
}
