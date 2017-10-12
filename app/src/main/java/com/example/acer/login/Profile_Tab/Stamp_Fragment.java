package com.example.acer.login.Profile_Tab;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Handler;
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
        ClipDrawable d1 = (ClipDrawable) ld.findDrawableByLayerId(R.id.progressshape);
        d1.setColorFilter(newColor, PorterDuff.Mode.SRC_IN);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
        maxExp = 100 + 30 * (Integer.parseInt(userLevel) - 1) * (Integer.parseInt(userLevel) + 6);
        myExp = Integer.parseInt(userExp);

        progressBar.setMax(maxExp);
        progressBar.setProgress(myExp);

        if(myExp >= maxExp) {
            UL++;
            }

        UL = Integer.parseInt(userLevel);

        switch (UL){

            case 1:
                wheel.setImageResource(R.drawable.woodwheel);
                levelname.setImageResource(R.drawable.woodname);
                levelbar.setImageResource(R.drawable.woodbar);
            if(myExp > maxExp * 0.0 && myExp <= maxExp * 0.33) {
                setProgressBarColor(progressBar, Color.parseColor("#edb043"));
            } else if(myExp > maxExp*0.33 && myExp <= maxExp * 0.66) {
                setProgressBarColor(progressBar, Color.parseColor("#af740b"));
            } else if(myExp > maxExp*0.66 && myExp <= maxExp * 1.0) {
                setProgressBarColor(progressBar, Color.parseColor("#7a520a"));
            }
                break;
            case 2:
                wheel.setImageResource(R.drawable.stonewheel);
                levelname.setImageResource(R.drawable.stonename);
                levelbar.setImageResource(R.drawable.stonebar);
            if(myExp > maxExp * 0.0 && myExp <= maxExp * 0.33) {
                setProgressBarColor(progressBar, Color.parseColor("#e5e5e5"));
            } else if(myExp > maxExp*0.33 && myExp <= maxExp * 0.66) {
                setProgressBarColor(progressBar, Color.parseColor("#bababa"));
            } else if(myExp > maxExp*0.66 && myExp <= maxExp * 1.0) {
                setProgressBarColor(progressBar, Color.parseColor("#7c7c7c"));
            }
                break;
            case 3:
                wheel.setImageResource(R.drawable.tirewheel);
                levelname.setImageResource(R.drawable.tirename);
                levelbar.setImageResource(R.drawable.tirebar);
            if(myExp > maxExp * 0.0 && myExp <= maxExp * 0.33) {
                setProgressBarColor(progressBar, Color.parseColor("#718aa8"));
            } else if(myExp > maxExp*0.33 && myExp <= maxExp * 0.66) {
                setProgressBarColor(progressBar, Color.parseColor("#47557f"));
            } else if(myExp > maxExp*0.66 && myExp <= maxExp * 1.0) {
                setProgressBarColor(progressBar, Color.parseColor("#1f2a49"));
            }
                break;
            case 4:
                wheel.setImageResource(R.drawable.silverwheel);
                levelname.setImageResource(R.drawable.silvername);
                levelbar.setImageResource(R.drawable.silverbar);
            if(myExp > maxExp * 0.0 && myExp <= maxExp * 0.33) {
                setProgressBarColor(progressBar, Color.parseColor("#e5e5e5"));
            } else if(myExp > maxExp*0.33 && myExp <= maxExp * 0.66) {
                setProgressBarColor(progressBar, Color.parseColor("#99999"));
            } else if(myExp > maxExp*0.66 && myExp <= maxExp * 1.0) {
                setProgressBarColor(progressBar, Color.parseColor("#666666"));
            }
                break;
            case 5:
                wheel.setImageResource(R.drawable.goldwheel);
                levelname.setImageResource(R.drawable.goldname);
                levelbar.setImageResource(R.drawable.goldbar);
            if(myExp > maxExp * 0.0 && myExp <= maxExp * 0.33) {
                setProgressBarColor(progressBar, Color.parseColor("#ffce00"));
            } else if(myExp > maxExp*0.33 && myExp <= maxExp * 0.66) {
                setProgressBarColor(progressBar, Color.parseColor("#f99f00"));
            } else if(myExp > maxExp*0.66 && myExp <= maxExp * 1.0) {
                setProgressBarColor(progressBar, Color.parseColor("#f97600"));
            }
                break;
            case 6:
                wheel.setImageResource(R.drawable.diamondwheel);
                levelname.setImageResource(R.drawable.diamondname);
                levelbar.setImageResource(R.drawable.diamondbar);
            if(myExp > maxExp * 0.0 && myExp <= maxExp * 0.33) {
                setProgressBarColor(progressBar, Color.parseColor("#e6f0fc"));
            } else if(myExp > maxExp*0.33 && myExp <= maxExp * 0.66) {
                setProgressBarColor(progressBar, Color.parseColor("#b4cbf2"));
            } else if(myExp > maxExp*0.66 && myExp <= maxExp * 1.0) {
                setProgressBarColor(progressBar, Color.parseColor("#8da8e2"));
            }
                break;

        }
        return rootView;
    }

}

