package com.example.acer.login.Profile_Tab;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.acer.login.Profile_Tab.DD119_Related.TabMenu1;
import com.example.acer.login.Profile_Tab.DD119_Related.TabMenu2;
import com.example.acer.login.Profile_Tab.DD119_Related.TabMenu3;
import com.example.acer.login.Profile_Tab.DD119_Related.TabMenu4;
import com.example.acer.login.Profile_Tab.DD119_Related.TabMenu5;
import com.example.acer.login.Profile_Tab.DD119_Related.TabMenu6;
import com.example.acer.login.R;

public class DD119_Fragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_dd119,container,false);

        Button button10 = (Button) rootView.findViewById(R.id.button10);
        button10.setOnClickListener(new Button.OnClickListener() {
                                        public void onClick(View v) {
                                            Intent intent = new Intent(getActivity().getApplication(),
                                                    TabMenu1.class);
                                            startActivity(intent);

                                        }
                                    }
        );


        Button button9 = (Button) rootView.findViewById(R.id.button9);
        button9.setOnClickListener(new Button.OnClickListener() {
                                       public void onClick(View v) {
                                           Intent intent = new Intent(getActivity().getApplication(),
                                                   TabMenu2.class);
                                           startActivity(intent);

                                       }
                                   }
        );
        // 버튼9 에서 tabmenu2 이동 끝


        // 버튼 에서 tabmenu3 이동 시작
        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new Button.OnClickListener() {
                                      public void onClick(View v) {
                                          Intent intent = new Intent(getActivity().getApplication(),
                                                  TabMenu3.class);
                                          startActivity(intent);

                                      }
                                  }
        );
        // 버튼 에서 tabmenu3 이동 끝

        // 버튼 에서 tabmenu4 이동 시작
        Button button13 = (Button) rootView.findViewById(R.id.button13);
        button13.setOnClickListener(new Button.OnClickListener() {
                                        public void onClick(View v) {
                                            Intent intent = new Intent(getActivity().getApplication(),
                                                    TabMenu4.class);
                                            startActivity(intent);

                                        }
                                    }
        );
        // 버튼 에서 tabmenu4 이동 끝

        // 버튼 에서 tabmenu5 이동 시작
        Button button12 = (Button) rootView.findViewById(R.id.button12);
        button12.setOnClickListener(new Button.OnClickListener() {
                                        public void onClick(View v) {
                                            Intent intent = new Intent(getActivity().getApplication(),
                                                    TabMenu5.class);
                                            startActivity(intent);

                                        }
                                    }
        );

        // 버튼 에서 tabmenu5 이동 끝

        // 버튼 에서 tabmenu6 이동 시작
        Button button11 = (Button) rootView.findViewById(R.id.button11);
        button11.setOnClickListener(new Button.OnClickListener() {
                                        public void onClick(View v) {
                                            Intent intent = new Intent(getActivity().getApplication(),
                                                    TabMenu6.class);
                                            startActivity(intent);

                                        }
                                    }
        );
        // 버튼 에서 tabmenu6 이동 끝
        return rootView;
    }
}
