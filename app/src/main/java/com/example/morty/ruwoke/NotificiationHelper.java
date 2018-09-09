package com.example.morty.ruwoke;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;

import android.graphics.BitmapFactory;
import android.os.Message;
import android.support.v4.app.RemoteInput;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotificiationHelper extends ContextWrapper {
    public static final String channel2ID = "channel2ID";
    public static final String channe2Name = "Channel 2";
    Random r = new Random();
    Random f = new Random();
    public static final int a = (int) (10 * Math.random()) + 1;
    public static final int b = (int) (10 * Math.random()) + 1;
    private NotificationManager mManager;
    static List<com.example.morty.ruwoke.Message> MESSAGES = new ArrayList<>();
    public NotificiationHelper(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels();
        }

    }

    @TargetApi(Build.VERSION_CODES.O)
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
        RemoteInput remoteInput = new RemoteInput.Builder("User_Answer")
                .setLabel("Your Answer...")
                .build();
        Intent replyintent = new Intent(this, DirectReply.class);
        PendingIntent replypendingintent = PendingIntent.getBroadcast(this,0,replyintent,0);

        NotificationCompat.Action replyAction = new NotificationCompat.Action.Builder(
                R.drawable.ic_menu_send,
                "Answer",
                replypendingintent
        ).addRemoteInput(remoteInput).build();


        NotificationCompat.MessagingStyle messagingStyle =
                new NotificationCompat.MessagingStyle("Me");
        messagingStyle.setConversationTitle("RUWOKE Alarm");

        MESSAGES.add(new com.example.morty.ruwoke.Message("Are you Awake? Try answering this question: " +a+" + "+b, "RUWOKE"));
        for (com.example.morty.ruwoke.Message chatMessage: MESSAGES){
            NotificationCompat.MessagingStyle.Message notificationMessage =
                    new NotificationCompat.MessagingStyle.Message(
                            chatMessage.getText(),
                            chatMessage.getTimestamp(),
                            chatMessage.getSender()
                    );
            messagingStyle.addMessage(notificationMessage);
        }
        return new NotificationCompat.Builder(getApplicationContext(), channel2ID)
                .setContentTitle(title)
                .setStyle(messagingStyle)
                .addAction(replyAction)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_alarm)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.appicon));

    }


}
