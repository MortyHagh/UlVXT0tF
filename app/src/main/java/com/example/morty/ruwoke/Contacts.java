package com.example.morty.ruwoke;


import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.content.Context;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class Contacts extends Fragment {

    //ListView contactList;
    String contactNames;
    long contactNumbers;

    public Contacts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //ContentResolver cr = Context.getContentResolver();
        //Cursor cur =

        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

}
