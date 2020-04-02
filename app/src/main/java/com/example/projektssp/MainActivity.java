package com.example.projektssp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaPlayer menuMusic = MediaPlayer.create(
                getApplicationContext(),
                R.raw.backgroundmusic);

        menuMusic.start();
        menuMusic.setVolume(0.05f, 0.05f);
        menuMusic.setLooping(true);

        // https://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity
        Button start = (Button)findViewById(R.id.startbutton);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GameMode.class));
            }
        });

    }




}
