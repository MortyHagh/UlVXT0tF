package com.example.morty.ruwoke;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    @NonNull
    @Override

    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(),R.style.TimePicker,this, hour, minute, true);
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.i("ontimesetcheck", "User Input came through");
        Alarms fragment1 = (Alarms) getActivity().getSupportFragmentManager().findFragmentByTag("Alarms");
        fragment1.alarmtext.setText("Current Alarm:\n"+hourOfDay + ":" + minute);
    }
}
