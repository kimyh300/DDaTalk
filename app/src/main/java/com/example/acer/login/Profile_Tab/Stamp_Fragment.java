package com.example.acer.login.Profile_Tab;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.acer.login.Login_Related.SharedPrefManager;
import com.example.acer.login.Profile_Tab.Stamp_Related.Stamp_Fragment_sub;
import com.example.acer.login.R;


public class Stamp_Fragment extends Fragment {
    String userLevel, userExp;
    TextView showLevel;
    String set;

    ProgressBar progressBar;
    Handler handler;

    int maxExp = 0;
    int myExp = 0;
    int UL;

    ImageView wheel;
    ImageView levelname;
    ImageView levelbar;
    ImageButton stampcollect;

    public void setProgressBarColor(ProgressBar progressBar, int newColor){
        LayerDrawable ld = (LayerDrawable) progressBar.getProgressDrawable();
        ClipDrawable d1 = (ClipDrawable) ld.findDrawableByLayerId(R.id.progressBar);
        d1.setColorFilter(newColor, PorterDuff.Mode.SRC_IN);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_stamp, container, false);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        showLevel = (TextView) rootView.findViewById(R.id.showLevel);
        wheel = (ImageView) rootView.findViewById(R.id.wheel);
        levelname = (ImageView) rootView.findViewById(R.id.levelname);
        levelbar = (ImageView) rootView.findViewById(R.id.levelbar);
        SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(rootView.getContext());
        userLevel = sharedPrefManager.getUserLevel();
        userExp = sharedPrefManager.getUserEXP();
        set = "현재레벨은  " + userLevel +"이며, " + "다음레벨까지 " + (maxExp-myExp) +"남았습니다.";
        showLevel.setText(set);




// 서브스탬프로 들어가기
        stampcollect = (ImageButton) rootView.findViewById(R.id.stampcollect);
        stampcollect.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Create new fragment and transaction
                Fragment anyFragment = new Stamp_Fragment_sub();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, anyFragment);
                transaction.addToBackStack(null);

                transaction.commit();

            }
        });

//레벨에 따른 이미지 변경
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


        if(myExp > maxExp * 0.0 && myExp <= maxExp * 0.33) {
            setProgressBarColor(progressBar, Color.parseColor("#edb043"));
        } else if(myExp > maxExp*0.33 && myExp <= maxExp * 0.66) {
            setProgressBarColor(progressBar, Color.parseColor("#af740b"));
        } else if(myExp > maxExp*0.66 && myExp <= maxExp * 1.0) {
            setProgressBarColor(progressBar, Color.parseColor("#7a520a"));

        }




        switch (UL){

            case 1:
                wheel.setImageResource(R.drawable.woodwheel);
                levelname.setImageResource(R.drawable.woodname);
                levelbar.setImageResource(R.drawable.woodbar);
                break;
            case 2:
                wheel.setImageResource(R.drawable.stonewheel);
                levelname.setImageResource(R.drawable.stonename);
                levelbar.setImageResource(R.drawable.stonebar);
                break;
            case 3:
                wheel.setImageResource(R.drawable.tirewheel);
                levelname.setImageResource(R.drawable.tirename);
                levelbar.setImageResource(R.drawable.tirebar);
                break;
            case 4:
                wheel.setImageResource(R.drawable.silverwheel);
                levelname.setImageResource(R.drawable.silvername);
                levelbar.setImageResource(R.drawable.silverbar);
                break;
            case 5:
                wheel.setImageResource(R.drawable.goldwheel);
                levelname.setImageResource(R.drawable.goldname);
                levelbar.setImageResource(R.drawable.goldbar);
                break;
            case 6:
                wheel.setImageResource(R.drawable.diamondwheel);
                levelname.setImageResource(R.drawable.diamondname);
                levelbar.setImageResource(R.drawable.diamondbar);
                break;

        }
        return rootView;
    }

}

