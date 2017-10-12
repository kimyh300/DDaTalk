package com.example.acer.login.Profile_Tab;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.acer.login.Profile_Tab.DD119_Related.tab_menu1;
import com.example.acer.login.Profile_Tab.DD119_Related.tab_menu2;
import com.example.acer.login.Profile_Tab.DD119_Related.tab_menu3;
import com.example.acer.login.Profile_Tab.DD119_Related.tab_menu4;
import com.example.acer.login.Profile_Tab.DD119_Related.tab_menu5;
import com.example.acer.login.Profile_Tab.DD119_Related.tab_menu6;
import com.example.acer.login.R;

public class DD119_Fragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_dd119,container,false);

        ImageButton notice_btn = (ImageButton) rootView.findViewById(R.id.notice);
        notice_btn.setOnClickListener(new Button.OnClickListener() {
                                        public void onClick(View v) {
                                            Intent intent = new Intent(getActivity().getApplication(),
                                                    tab_menu1.class);
                                            startActivity(intent);

                                        }
                                    }
        );


        ImageButton accident_btn = (ImageButton) rootView.findViewById(R.id.accident);
        accident_btn.setOnClickListener(new Button.OnClickListener() {
                                          public void onClick(View v) {
                                              Intent intent = new Intent(getActivity().getApplication(),
                                                      tab_menu2.class);
                                              startActivity(intent);

                                          }
                                      }
        );

        ImageButton lost_damage_btn = (ImageButton) rootView.findViewById(R.id.lost_damage);
        lost_damage_btn.setOnClickListener(new Button.OnClickListener() {
                                          public void onClick(View v) {
                                              Intent intent = new Intent(getActivity().getApplication(),
                                                      tab_menu3.class);
                                              startActivity(intent);

                                          }
                                      }
        );

        ImageButton break_handle_btn = (ImageButton) rootView.findViewById(R.id.break_handle);
        break_handle_btn.setOnClickListener(new Button.OnClickListener() {
                                          public void onClick(View v) {
                                              Intent intent = new Intent(getActivity().getApplication(),
                                                      tab_menu4.class);
                                              startActivity(intent);

                                          }
                                      }
        );

        ImageButton tire_chain_btn = (ImageButton) rootView.findViewById(R.id.tire_chain);
        tire_chain_btn.setOnClickListener(new Button.OnClickListener() {
                                          public void onClick(View v) {
                                              Intent intent = new Intent(getActivity().getApplication(),
                                                      tab_menu5.class);
                                              startActivity(intent);

                                          }
                                      }
        );


        ImageButton saddle_btn = (ImageButton) rootView.findViewById(R.id.saddle);
        saddle_btn.setOnClickListener(new Button.OnClickListener() {
                                          public void onClick(View v) {
                                              Intent intent = new Intent(getActivity().getApplication(),
                                                      tab_menu6.class);
                                              startActivity(intent);

                                          }
                                      }
        );

        return rootView;
    }
}
