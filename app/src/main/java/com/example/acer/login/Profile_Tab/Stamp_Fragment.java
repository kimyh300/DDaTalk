package com.example.acer.login.Profile_Tab;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.acer.login.Login_Related.SharedPrefManager;
import com.example.acer.login.R;


public class Stamp_Fragment extends Fragment {
    String userLevel, userExp;
    TextView showLevel, textView3;
    String set;

    ProgressBar progressBar;
    Handler handler;

    int maxExp = 0;
    int myExp = 0;
    int UL;

    ImageView wheel;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_stamp, container, false);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        textView3 = (TextView) rootView.findViewById(R.id.textView3);
        showLevel = (TextView) rootView.findViewById(R.id.showLevel);
        wheel =(ImageView)rootView.findViewById(R.id.wheel);
        SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(rootView.getContext());
        userLevel = sharedPrefManager.getUserLevel();
        userExp = sharedPrefManager.getUserEXP();
        set = "레벨 : " + userLevel + "\n경험치 : " + userExp;
        showLevel.setText(set);



        handler = new Handler() {
            public void handleMessage(Message message) {
                maxExp = 100 + 30 * (Integer.parseInt(userLevel) - 1) * (Integer.parseInt(userLevel) + 6);
                myExp = Integer.parseInt(userExp);

                progressBar.setMax(maxExp);
                progressBar.setProgress(myExp);

                handler.sendEmptyMessageDelayed(10, 1000);
            }
        };
        handler.sendEmptyMessageDelayed(10, 0);

        UL = Integer.parseInt(userLevel);
//        UL= UL-1;
        switch (UL){
            case 1:
                wheel.setImageResource(R.drawable.woodwheel);
                break;
            case 2:
                wheel.setImageResource(R.drawable.stonewheel);
                break;
            case 3:
                wheel.setImageResource(R.drawable.tirewheel);
                break;
            case 4:
                wheel.setImageResource(R.drawable.silverwheel);
                break;
            case 5:
                wheel.setImageResource(R.drawable.goldwheel);
                break;
            case 6:
                wheel.setImageResource(R.drawable.diamondwheel);
                break;

        }





        return rootView;
    }

}






