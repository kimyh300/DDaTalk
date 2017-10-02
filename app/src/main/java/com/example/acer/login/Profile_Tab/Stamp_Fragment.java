package com.example.acer.login.Profile_Tab;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.acer.login.Login_Related.SharedPrefManager;
import com.example.acer.login.R;


public class Stamp_Fragment extends Fragment{
    String userLevel,userExp;
    TextView showLevel, textView3;
    String set;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_stamp,container,false);

        textView3 = (TextView)rootView.findViewById(R.id.textView3);
        showLevel = (TextView)rootView.findViewById(R.id.showLevel);
        SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(rootView.getContext());
        userLevel = sharedPrefManager.getUserLevel();
        userExp = sharedPrefManager.getUserEXP();
        set = "레벨 : "+userLevel+"\n경험치 : "+userExp;

        showLevel.setText(set);




        return rootView;
    }




}
