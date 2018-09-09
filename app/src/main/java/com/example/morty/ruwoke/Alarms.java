package com.example.morty.ruwoke;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;


/**
 * A simple {@link Fragment} subclass.
 */
public class Alarms extends Fragment {

    private static final String TAG = "Alarms";
    public TextView alarmtext;
    private Button time_pick;
    private Button cancel_alarm_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_alarms, container, false);
        alarmtext = v.findViewById(R.id.current_alarm);
        time_pick = (Button)v.findViewById(R.id.time_pick_toggle);
        cancel_alarm_button = (Button)v.findViewById(R.id.cancel_alarm);

        time_pick.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View s){

                time_pick.setVisibility(View.GONE);
                cancel_alarm_button.setVisibility(View.VISIBLE);
                DialogFragment timepicker = new TimePickerFragment();
                timepicker.show(getFragmentManager(), "time picker");

            }

        });

        cancel_alarm_button.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View s){
                AlarmManager alarmManager_cancel_button = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(getActivity(), AlertReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 1, intent, 0);

                alarmManager_cancel_button.cancel(pendingIntent);
                Log.e("CancelStatus", "Alarm Cancelled");
                alarmtext.setText("Hello PennApps!");
                cancel_alarm_button.setVisibility(View.GONE);
                time_pick.setVisibility(View.VISIBLE);

            }

        });

        return v;
    }

}
