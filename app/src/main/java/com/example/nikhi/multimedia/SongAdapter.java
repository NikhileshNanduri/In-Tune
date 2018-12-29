package com.example.nikhi.multimedia;

import android.content.Context;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends BaseAdapter{

    private Context context;
    private int layout;
    private ArrayList<Song> arrayList;

    private MediaPlayer mediaPlayer;
    private boolean flag = true;


    SongAdapter(Context context, int layout, ArrayList<Song> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtName , txtSinger;
        ImageView playButton , stopButton;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(layout , null);

            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtName);
            viewHolder.txtSinger = (TextView) convertView.findViewById(R.id.txtSinger);
            viewHolder.playButton = (ImageView) convertView.findViewById(R.id.playButton);
            viewHolder.stopButton = (ImageView) convertView.findViewById(R.id.stopButton);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Song song = arrayList.get(position);

        viewHolder.txtName.setText(song.getName());
        viewHolder.txtSinger.setText(song.getSinger());

        viewHolder.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    mediaPlayer = MediaPlayer.create(context, song.getSong());
                    flag = false;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    viewHolder.playButton.setImageResource(R.drawable.play_new);
                }
                else {
                    mediaPlayer.start();
                    viewHolder.playButton.setImageResource(R.drawable.pause_new);
                }
            }
        });

        viewHolder.stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flag){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    flag = true;

                }
                viewHolder.playButton.setImageResource(R.drawable.play_new);

            }
        });
        return convertView;
    }
}