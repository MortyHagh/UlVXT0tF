package com.example.morty.ruwoke;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;

public class TextSender extends Activity {
    public void sendSMS(Activity act, String phoneNumber, String message)
    {
        PendingIntent pi = PendingIntent.getActivity(act, 0,
                new Intent(act, TextSender.class), 0);
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, pi, null);

        Log.e("MESSAGE TAG:", message);
    }
}
