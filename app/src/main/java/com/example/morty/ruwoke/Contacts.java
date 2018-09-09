package com.example.morty.ruwoke;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class Contacts extends Fragment {
    ////////////////////////////////////////////////////////
    // This Code was made Possible by Brian DelRocini :) //
    //////////////////////////////////////////////////////

    EditText nameEdit, phoneEdit;
    public String contactPhone, contactName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_contacts, container, false);
        nameEdit = (EditText)v.findViewById(R.id.editName);
        phoneEdit = (EditText)v.findViewById(R.id.editPhone);
        final Button addButton = (Button)v.findViewById(R.id.buttonAdd);

        nameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addButton.setEnabled(!nameEdit.getText().toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactPhone = phoneEdit.getText().toString().trim();
                contactName = nameEdit.getText().toString().trim();

                Context context = getActivity(); // or getActivity(); in case of Fragments
                SharedPreferences pref = context.getSharedPreferences(
                        "MyPrefs", Context.MODE_PRIVATE);
// Here we are passing a unique name identifier for preference and mode applicable
                // We need an editor object to make changes
                SharedPreferences.Editor edit = pref.edit();

// Store data. you may also use putFloat(), putInt(), putLong() as requirement
                edit.putString("PhoneNumber",contactPhone);
                edit.putString("ContactName",contactName);

// Commit the changes
                edit.commit();
            }
        });
        return v;
    }

}
