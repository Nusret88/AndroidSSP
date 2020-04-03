package com.example.projektssp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

// https://stackoverflow.com/questions/16690057/how-can-i-stop-media-player-in-another-activity
public class AudioPlay {

    public static MediaPlayer backgroundMusic;
    public static boolean isplayingAudio = false;

    public static void playAudio(Context c, int id){
        backgroundMusic = MediaPlayer.create(c,id);

        if(!backgroundMusic.isPlaying())
        {
            isplayingAudio = true;
            backgroundMusic.setVolume(0.05f, 0.05f);
            backgroundMusic.setLooping(true);
            backgroundMusic.start();
        }
    }

    public static void stopAudio(){
        isplayingAudio = false;
        backgroundMusic.stop();
    }
}
