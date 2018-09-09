package com.example.morty.ruwoke;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class RingtonePlayingService extends Service {

    public static MediaPlayer media_song;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        media_song = MediaPlayer.create(this, R.raw.star_wars_theme);
        media_song.setLooping(true);
        media_song.start();
        Log.e("RingtoneService", "Ringing!!");
        return START_NOT_STICKY;
    }
}
