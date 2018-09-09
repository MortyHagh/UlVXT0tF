package com.example.morty.ruwoke;


import android.app.Activity;
import android.app.Notification;

import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

public class AlertReceiver extends BroadcastReceiver {

    private NotificationManagerCompat notificationManager;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Alarm_Trigger", "Alarm is Triggered");
        Intent service_intent = new Intent(context, RingtonePlayingService.class);
        context.startService(service_intent);

        NotificiationHelper notificiationHelper = new NotificiationHelper(context);
        NotificationCompat.Builder nb = notificiationHelper.getChannelNotification("RUWOKE", "WAKE UP!!");
        notificiationHelper.getManager().notify(1, nb.build());

    }


}
