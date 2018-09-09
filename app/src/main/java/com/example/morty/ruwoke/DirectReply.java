package com.example.morty.ruwoke;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.util.Log;


public class DirectReply extends BroadcastReceiver {
public String contact_Phone,contact_Name;
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);

        Log.e("DirectReply", "Accessed");
        Intent i = new Intent(context,TextSender.class);
        if (remoteInput != null) {
            CharSequence replyText = remoteInput.getCharSequence("User_Answer");
            Message answer = new Message(replyText, null);
           NotificiationHelper.MESSAGES.add(answer);
            NotificiationHelper notificiationHelper = new NotificiationHelper(context);
            NotificationCompat.Builder nb = notificiationHelper.getChannelNotification("RUWOKE", "WAKE UP!!");

           if (Integer.parseInt(replyText.toString()) == NotificiationHelper.a + NotificiationHelper.b )
           {
               Log.e("AnswerCorrectness", "Correct!!!");

               RingtonePlayingService.media_song.stop();
               Message RUWOKE_Alarm_Cancelled = new Message("Alarm is Dismissed.", null);
               NotificiationHelper.MESSAGES.add(RUWOKE_Alarm_Cancelled);


           }
           else {
               SharedPreferences pref = context.getSharedPreferences("MyPrefs", context.MODE_PRIVATE);

               // we will pass the key and default value in the second field
                   String phone = pref.getString("PhoneNumber", "");
                   String Name = pref.getString("ContactName", "");

                   TextSender ts = new TextSender();
               ts.sendSMS(phone,"Hi "+Name+", Please come wake me up! I am still sleeping. (Text sent by RUWoke)");
               RingtonePlayingService.media_song.stop();
               Message RUWOKE_Alarm_Text_Sent = new Message("A Text Message has been sent to your contact.", null);
               NotificiationHelper.MESSAGES.add(RUWOKE_Alarm_Text_Sent);

           }
        }
    }
}