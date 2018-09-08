package com.example.morty.ruwoke;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import java.nio.channels.Channel;

public class App extends Application {
    public static final String Channel_1_ID = "channel1";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationsChannels();
    }

    private void createNotificationsChannels() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel1 = new NotificationChannel(
                    Channel_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("Wake up!");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }

    }
}
