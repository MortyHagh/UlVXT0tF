package com.example.morty.ruwoke;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.Toast;

import java.sql.Time;


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

            SharedPreferences pref = context.getSharedPreferences("MyPrefs", context.MODE_PRIVATE);

           if (Integer.parseInt(replyText.toString()) == NotificiationHelper.a + NotificiationHelper.b )
           {
               Log.e("AnswerCorrectness", "Correct!!!");
               SharedPreferences.Editor edit = pref.edit();
               edit.putInt("SnoozeLimit",0);
               edit.commit();
               RingtonePlayingService.media_song.stop();
               CancelAlarm(context);
               Message RUWOKE_Alarm_Cancelled = new Message("Alarm is Dismissed.", null);
               NotificiationHelper.MESSAGES.add(RUWOKE_Alarm_Cancelled);
           }
           else {


               // we will pass the key and default value in the second field
                   String phone = pref.getString("PhoneNumber", "");
                   String Name = pref.getString("ContactName", "");
                    Integer limit = pref.getInt("SnoozeLimit", 0);

                    if (limit <= 1)
                    {CancelAlarm(context);
                        SnoozeAlarm(context);
                        SharedPreferences.Editor edit = pref.edit();
                        edit.putInt("SnoozeLimit",limit+1);
                        edit.commit();
                        Message RUWOKE_Alarm_Snooze = new Message("Alarm Snoozed!", null);
                        NotificiationHelper.MESSAGES.add(RUWOKE_Alarm_Snooze);
                    }
                    else{
                        CancelAlarm(context);
                        TextSender ts = new TextSender();
                        ts.sendSMS(phone,"Hi "+Name+", Please come wake me up! I am still sleeping. (Text sent by RUWoke)");
                        Message RUWOKE_Alarm_Text_Sent = new Message("A Text Message has been sent to your contact.", null);
                        NotificiationHelper.MESSAGES.add(RUWOKE_Alarm_Text_Sent);
                    }
               RingtonePlayingService.media_song.stop();


              // TimePickerFragment tpf = new TimePickerFragment();

           }
        }
    }
    public void CancelAlarm(Context context)
    {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        Intent intent1 = new Intent(context, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent1, 0);

        alarmManager.cancel(pendingIntent);
        Toast.makeText(context, "Alarm Cancelled.",
                Toast.LENGTH_LONG).show();
    }

    public void SnoozeAlarm (Context context)
    {


        long currentTimeMillis = System.currentTimeMillis();
        long nextUpdateTimeMillis = currentTimeMillis + 30*DateUtils.SECOND_IN_MILLIS;
        //Time nextUpdateTime = new Time().setTime(nextUpdateTimeMillis);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent, 0);
        AlarmManager alarmManager_snooze = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager_snooze.set(AlarmManager.RTC, nextUpdateTimeMillis, pendingIntent);

        Toast.makeText(context, "Snoozed for a minute.",
                Toast.LENGTH_LONG).show();

    }
}