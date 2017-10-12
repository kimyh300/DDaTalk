package com.example.acer.login.Profile_Tab.Stamp_Related;


import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.acer.login.Login_Related.SharedPrefManager;
import com.example.acer.login.Profile_Tab.Stamp_Fragment;
import com.example.acer.login.R;

import java.util.ArrayList;

public class Stamp_Fragment_sub extends Fragment {


    String userLevel;
    ImageView woodwheelmap;
    ImageView stonewheelmap;
    ImageView tirewheelmap;
    ImageView silverwheelmap;
    ImageView goldwheelmap;
    ImageView diamondwheelmap;
    ImageButton backtostamp;

    Drawable changewheelmap;
    ArrayList<Drawable> wheelmapList = new ArrayList<Drawable>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_stamp_sub, container, false);

        // 스탬프페이지로 돌아가기
        backtostamp = (ImageButton) rootView.findViewById(R.id.backtostamp);
        backtostamp.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Create new fragment and transaction
                Fragment someFragment = new Stamp_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, someFragment);
                transaction.addToBackStack(null);

                transaction.commit();

            }
        });


        //로그인한 유저레벨 가져오기
        userLevel = SharedPrefManager.getInstance(getActivity().getApplicationContext()).getUserLevel();


        Resources res = getResources();
        wheelmapList.add(res.getDrawable(R.drawable.woodwheelmap));
        wheelmapList.add(res.getDrawable(R.drawable.stonewheelmap));
        wheelmapList.add(res.getDrawable(R.drawable.tirewheelmap));
        wheelmapList.add(res.getDrawable(R.drawable.silverwheelmap));
        wheelmapList.add(res.getDrawable(R.drawable.goldwheelmap));
        wheelmapList.add(res.getDrawable(R.drawable.diamondwheelmap));
        wheelmapList.add(res.getDrawable(R.drawable.needlevelup));


        //뷰객체 보여주
        woodwheelmap = (ImageView) rootView.findViewById(R.id.woodwheelmap);
        stonewheelmap = (ImageView) rootView.findViewById(R.id.stonewheelmap);
        tirewheelmap = (ImageView) rootView.findViewById(R.id.tirewheelmap);
        silverwheelmap = (ImageView) rootView.findViewById(R.id.silverwheelmap);
        goldwheelmap = (ImageView) rootView.findViewById(R.id.goldwheelmap);
        diamondwheelmap = (ImageView) rootView.findViewById(R.id.diamondwheelmap);




        switch (userLevel) {
            case "1":
                woodwheelmap.setImageDrawable(wheelmapList.get(0));
                stonewheelmap.setImageDrawable(wheelmapList.get(6));
                tirewheelmap.setImageDrawable(wheelmapList.get(6));
                silverwheelmap.setImageDrawable(wheelmapList.get(6));
                goldwheelmap.setImageDrawable(wheelmapList.get(6));
                diamondwheelmap.setImageDrawable(wheelmapList.get(6));
                break;
            case "2":
                woodwheelmap.setImageDrawable(wheelmapList.get(0));
                stonewheelmap.setImageDrawable(wheelmapList.get(1));
                tirewheelmap.setImageDrawable(wheelmapList.get(6));
                silverwheelmap.setImageDrawable(wheelmapList.get(6));
                goldwheelmap.setImageDrawable(wheelmapList.get(6));
                diamondwheelmap.setImageDrawable(wheelmapList.get(6));
                break;
            case "3":
                woodwheelmap.setImageDrawable(wheelmapList.get(0));
                stonewheelmap.setImageDrawable(wheelmapList.get(1));
                tirewheelmap.setImageDrawable(wheelmapList.get(2));
                silverwheelmap.setImageDrawable(wheelmapList.get(6));
                goldwheelmap.setImageDrawable(wheelmapList.get(6));
                diamondwheelmap.setImageDrawable(wheelmapList.get(6));
                break;
            case "4":
                woodwheelmap.setImageDrawable(wheelmapList.get(0));
                stonewheelmap.setImageDrawable(wheelmapList.get(1));
                tirewheelmap.setImageDrawable(wheelmapList.get(2));
                silverwheelmap.setImageDrawable(wheelmapList.get(3));
                goldwheelmap.setImageDrawable(wheelmapList.get(6));
                diamondwheelmap.setImageDrawable(wheelmapList.get(6));
                break;
            case "5":
                woodwheelmap.setImageDrawable(wheelmapList.get(0));
                stonewheelmap.setImageDrawable(wheelmapList.get(1));
                tirewheelmap.setImageDrawable(wheelmapList.get(2));
                silverwheelmap.setImageDrawable(wheelmapList.get(3));
                goldwheelmap.setImageDrawable(wheelmapList.get(4));
                diamondwheelmap.setImageDrawable(wheelmapList.get(6));
                break;
            case "6":
                woodwheelmap.setImageDrawable(wheelmapList.get(0));
                stonewheelmap.setImageDrawable(wheelmapList.get(1));
                tirewheelmap.setImageDrawable(wheelmapList.get(2));
                silverwheelmap.setImageDrawable(wheelmapList.get(3));
                goldwheelmap.setImageDrawable(wheelmapList.get(4));
                diamondwheelmap.setImageDrawable(wheelmapList.get(5));
                break;


        } return rootView;
    }

}