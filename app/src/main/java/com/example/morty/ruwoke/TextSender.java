package com.example.morty.ruwoke;

import android.telephony.SmsManager;
import android.util.Log;

public class TextSender {
    public void sendSMS(String phoneNumber, String message)
    {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);

        Log.e("MESSAGE TAG:", message);
    }
}
