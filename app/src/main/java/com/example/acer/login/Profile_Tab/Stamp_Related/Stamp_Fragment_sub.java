package com.example.acer.login.Profile_Tab.Stamp_Related;


import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.acer.login.R;

import java.util.ArrayList;

import static com.example.acer.login.R.drawable.stonewheelmap;
import static com.example.acer.login.R.drawable.woodwheelmap;

public class Stamp_Fragment_sub extends Fragment {

    ImageView woodmap;
    ImageView stonemap;
    ImageView tiremap;
    ImageView silvermap;
    ImageView goldmap;
    ImageView diamondmap;

    Drawable changewheelmap;
    ArrayList<Drawable> wheelmapList = new ArrayList<Drawable>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_stamp_sub, container, false);

        Resources res = getResources();
        wheelmapList.add(res.getDrawable(woodwheelmap));
        wheelmapList.add(res.getDrawable(stonewheelmap));
        wheelmapList.add(res.getDrawable(R.drawable.tirewheelmap));
        wheelmapList.add(res.getDrawable(R.drawable.silverwheelmap));
        wheelmapList.add(res.getDrawable(R.drawable.goldwheelmap));
        wheelmapList.add(res.getDrawable(R.drawable.diamondwheelmap));
        wheelmapList.add(res.getDrawable(R.drawable.needlevelup));


        //뷰객체 보여주


        woodmap = (ImageView) rootView.findViewById(R.id.woodmap);
        stonemap = (ImageView) rootView.findViewById(R.id.stonemap);
        tiremap = (ImageView) rootView.findViewById(R.id.tiremap);
        silvermap = (ImageView) rootView.findViewById(R.id.silvermap);
        goldmap = (ImageView) rootView.findViewById(R.id.goldmap);
        diamondmap = (ImageView) rootView.findViewById(R.id.diamondmap);


        int UL = 1;
        UL = UL++;
        switch (UL) {
            case 1:
                woodmap.setImageDrawable(wheelmapList.get(0));
                stonemap.setImageDrawable(wheelmapList.get(6));
                tiremap.setImageDrawable(wheelmapList.get(6));
                silvermap.setImageDrawable(wheelmapList.get(6));
                goldmap.setImageDrawable(wheelmapList.get(6));
                diamondmap.setImageDrawable(wheelmapList.get(6));
                break;
            case 2:
                stonemap.setImageDrawable(wheelmapList.get(1));
                tiremap.setImageDrawable(wheelmapList.get(6));
                silvermap.setImageDrawable(wheelmapList.get(6));
                goldmap.setImageDrawable(wheelmapList.get(6));
                diamondmap.setImageDrawable(wheelmapList.get(6));
                break;
            case 3:
                tiremap.setImageDrawable(wheelmapList.get(2));
                silvermap.setImageDrawable(wheelmapList.get(6));
                goldmap.setImageDrawable(wheelmapList.get(6));
                diamondmap.setImageDrawable(wheelmapList.get(6));
                break;
            case 4:
                silvermap.setImageDrawable(wheelmapList.get(3));
                goldmap.setImageDrawable(wheelmapList.get(6));
                diamondmap.setImageDrawable(wheelmapList.get(6));
                break;
            case 5:
                goldmap.setImageDrawable(wheelmapList.get(4));
                goldmap.setImageDrawable(wheelmapList.get(6));
                break;
            case 6:
                goldmap.setImageDrawable(wheelmapList.get(5));
                break;



        } return rootView;
    }

}