package com.example.morty.ruwoke;


import android.app.TimePickerDialog;
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



    public Alarms() {
        // Required empty public constructor
    }
    private static final String TAG = "Alarms";
    public TextView alarmtext;
    private Button time_pick;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_alarms, container, false);
        alarmtext = v.findViewById(R.id.current_alarm);
        time_pick = (Button)v.findViewById(R.id.time_pick_toggle);
        time_pick.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View s){
                DialogFragment timepicker = new TimePickerFragment();
                timepicker.show(getFragmentManager(), "time picker");

            }

        });

        return v;
    }

}
