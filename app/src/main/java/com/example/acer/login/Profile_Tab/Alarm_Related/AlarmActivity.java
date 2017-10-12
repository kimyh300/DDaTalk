package com.example.acer.login.Profile_Tab.Alarm_Related;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.acer.login.R;

public class AlarmActivity extends AppCompatActivity {

    ListView listViewAlarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        //noinspection ConstantConditions
        getSupportActionBar().hide();

        listViewAlarm = (ListView)findViewById(R.id.listViewAlarm);
    }

}
