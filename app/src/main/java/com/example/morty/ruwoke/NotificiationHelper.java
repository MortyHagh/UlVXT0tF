package com.example.morty.ruwoke;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class NotificiationHelper extends ContextWrapper {
    public static final String channel2ID = "channel2ID";
    public static final String channe2Name = "Channel 2";
    private NotificationManager mManager;
    public NotificiationHelper(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels();
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    public void createChannels(){
        NotificationChannel channel2 = new NotificationChannel(channel2ID,channe2Name,NotificationManager.IMPORTANCE_HIGH);
        channel2.enableLights(true);
        channel2.enableVibration(true);
        channel2.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        getManager().createNotificationChannel(channel2);
    }

    public NotificationManager getManager() {
        if (mManager == null)
        {mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        }

        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification (String title, String message)
    {

        return new NotificationCompat.Builder(getApplicationContext(), channel2ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_launcher_background);

    }


}
