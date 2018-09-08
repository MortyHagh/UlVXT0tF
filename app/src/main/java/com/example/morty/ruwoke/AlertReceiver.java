package com.example.morty.ruwoke;


import android.app.Notification;

import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlertReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Alarm_Trigger", "Alarm is Triggered");
    }
}
