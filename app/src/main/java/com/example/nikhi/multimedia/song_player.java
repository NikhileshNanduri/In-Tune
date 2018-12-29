package com.example.nikhi.multimedia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class song_player extends AppCompatActivity {

    private ArrayList<Song> arrayList;
    private SongAdapter songAdapter;
    private ListView songList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_player);

        songList = (ListView) findViewById(R.id.songList);
        arrayList = new ArrayList<>();
        arrayList.add(new Song("Aaftab" , "The Local Train" , R.raw.aaftab));
        arrayList.add(new Song("Alone" , "Marshmello" , R.raw.alone));
        arrayList.add(new Song("Animals" , "Martin Garrix" , R.raw.animals));
        arrayList.add(new Song("Titanium" , "David Guetta ft. Sia" , R.raw.titanium));
        arrayList.add(new Song("Unity" , "TheFatRat" , R.raw.unity));


        songAdapter = new SongAdapter(this , R.layout.song_item , arrayList);
        songList.setAdapter(songAdapter);

    }
}
