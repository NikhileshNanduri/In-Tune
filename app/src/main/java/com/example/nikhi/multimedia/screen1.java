package com.example.nikhi.multimedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class screen1 extends AppCompatActivity {

    private TextView textView, textview2;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1);

        textView = (TextView) findViewById(R.id.text);
        imageView = (ImageView) findViewById(R.id.image);
        textview2 = (TextView) findViewById(R.id.text2);

        Animation animation = AnimationUtils.loadAnimation(this , R.anim.transition);
        textView.startAnimation(animation);
        imageView.startAnimation(animation);
        textview2.startAnimation(animation);

        final Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Thread timer = new Thread()
        {
            public void run()
            {
                try{
                    sleep(3000);

                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
            timer.start();
    }
}
