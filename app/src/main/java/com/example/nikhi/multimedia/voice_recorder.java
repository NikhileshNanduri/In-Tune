package com.example.nikhi.multimedia;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class voice_recorder extends AppCompatActivity {

    Button btRecord , btStopRecord , btPlay , btStopPlay;

    String pathSave = "";
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

    final int REQUEST_PERMISSION_CODE = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_recorder);

        //RunTime Permissions
        if(!checkPermissionFromDevice()){
            requestPermissions();
        }


        btPlay = (Button) findViewById(R.id.startPlay);
        btStopPlay = (Button) findViewById(R.id.stopPlay);
        btRecord = (Button) findViewById(R.id.startRecord);
        btStopRecord = (Button) findViewById(R.id.stopRecord);


            btRecord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(checkPermissionFromDevice()) {


                        pathSave = Environment.getExternalStorageDirectory().getAbsolutePath() + "/"
                                + UUID.randomUUID().toString() + "_audio_record.3gp";

                        setUpMediaRecorder();
                        try {
                            mediaRecorder.prepare();
                            mediaRecorder.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        /*btStopRecord.setEnabled(true);*/
                        btPlay.setEnabled(false);
                        btStopPlay.setEnabled(false);

                        Toast.makeText(voice_recorder.this, "Recording...", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        requestPermissions();
                    }
                }
            });

            btStopRecord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaRecorder.stop();
                    btStopRecord.setEnabled(false);
                    btPlay.setEnabled(true);
                    btRecord.setEnabled(true);
                    btStopPlay.setEnabled(false);
                }
            });

            btPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btStopPlay.setEnabled(true);
                    btStopRecord.setEnabled(false);
                    btRecord.setEnabled(false);

                    mediaPlayer = new MediaPlayer();
                    try{
                        mediaPlayer.setDataSource(pathSave);
                        mediaPlayer.prepare();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }

                    mediaPlayer.start();
                    Toast.makeText(voice_recorder.this, "Playing...", Toast.LENGTH_SHORT).show();
                }
            });

            btStopPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btStopRecord.setEnabled(false);
                    btRecord.setEnabled(true);
                    btStopPlay.setEnabled(false);
                    btPlay.setEnabled(true);

                    if(mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        setUpMediaRecorder();
                    }

                }
            });


    }

    private void setUpMediaRecorder() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);

        mediaRecorder.setOutputFile(pathSave);

    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this , new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        }, REQUEST_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case REQUEST_PERMISSION_CODE :
            {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
                 break;
        }
    }

    private boolean checkPermissionFromDevice() {
        int write_external_storage_result = ContextCompat.checkSelfPermission(this , Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int record_audio_result = ContextCompat.checkSelfPermission(this , Manifest.permission.RECORD_AUDIO);

        return
                (write_external_storage_result == PackageManager.PERMISSION_GRANTED)
                        &&
                (record_audio_result == PackageManager.PERMISSION_GRANTED);

    }
}
