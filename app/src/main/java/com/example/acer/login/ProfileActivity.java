package com.example.acer.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.acer.login.Login_Related.LoginActivity;
import com.example.acer.login.Login_Related.SharedPrefManager;
import com.example.acer.login.Profile_Tab.DD119_Fragment;
import com.example.acer.login.Profile_Tab.Home_Fragment;
import com.example.acer.login.Profile_Tab.MyPage_Fragment;
import com.example.acer.login.Profile_Tab.Stamp_Fragment;
import com.example.acer.login.Profile_Tab.Write_Fragment;
import com.example.acer.login.Profile_Tab.Write_Related.FindSpot_Fragment;

public class ProfileActivity extends AppCompatActivity {

    //    private TextView textViewUsername, textViewUserEmail;
    TabLayout tabs;
    Home_Fragment homeFragment;
    Stamp_Fragment stampFragment;
    Write_Fragment writeFragment;
    DD119_Fragment dd119FRAGMENT;
    MyPage_Fragment myPageFragment;
    FindSpot_Fragment spotFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }


        homeFragment = new Home_Fragment();
        stampFragment = new Stamp_Fragment();
        writeFragment = new Write_Fragment();
        dd119FRAGMENT = new DD119_Fragment();
        myPageFragment = new MyPage_Fragment();
        spotFragment = new FindSpot_Fragment();


        getSupportFragmentManager().beginTransaction().add(R.id.container, homeFragment).commit();

        tabInit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);            //액션바 아이콘을 업 네비게이션 형태로 표시합니다.
        actionBar.setDisplayShowTitleEnabled(false);        //액션바에 표시되는 제목의 표시유무를 설정합니다.
        actionBar.setDisplayShowHomeEnabled(false);            //홈 아이콘을 숨김처리합니다.


        //layout을 가지고 와서 actionbar에 포팅을 시킵니다.
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionbar = inflater.inflate(R.layout.actionbar_layout, null);

        actionBar.setCustomView(actionbar);

        //액션바 양쪽 공백 없애기
        Toolbar parent = (Toolbar)actionbar.getParent();
        parent.setContentInsetsAbsolute(0,0);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.menuSettings:
                Toast.makeText(this, "You clicked settings", Toast.LENGTH_LONG).show();
                break;

        }
        return true;
    }


    void tabInit() {
        tabs = (TabLayout) findViewById(R.id.tabs);
        //noinspection ConstantConditions
        tabs.getTabAt(0).setIcon(R.drawable.home_active);
        //noinspection ConstantConditions
        tabs.getTabAt(1).setIcon(R.drawable.check_disabled);
        //noinspection ConstantConditions
        tabs.getTabAt(2).setIcon(R.drawable.add_disabled);
        //noinspection ConstantConditions
        tabs.getTabAt(3).setIcon(R.drawable.em_119_disabled);
        //noinspection ConstantConditions
        tabs.getTabAt(4).setIcon(R.drawable.my_page_disabled);
        //noinspection deprecation
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();

                Fragment selected = null;
                if (position == 0) {
                    selected = homeFragment;
                    //noinspection ConstantConditions
                    tabs.getTabAt(0).setIcon(R.drawable.home_active);
                    //noinspection ConstantConditions
                    tabs.getTabAt(1).setIcon(R.drawable.check_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(2).setIcon(R.drawable.add_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(3).setIcon(R.drawable.em_119_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(4).setIcon(R.drawable.my_page_disabled);
                } else if (position == 1) {
                    selected = stampFragment;
                    //noinspection ConstantConditions
                    tabs.getTabAt(0).setIcon(R.drawable.home_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(1).setIcon(R.drawable.check_active);
                    //noinspection ConstantConditions
                    tabs.getTabAt(2).setIcon(R.drawable.add_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(3).setIcon(R.drawable.em_119_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(4).setIcon(R.drawable.my_page_disabled);
                } else if (position == 2) {
                    selected = writeFragment;
                    //noinspection ConstantConditions
                    tabs.getTabAt(0).setIcon(R.drawable.home_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(1).setIcon(R.drawable.check_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(2).setIcon(R.drawable.add_active);
                    //noinspection ConstantConditions
                    tabs.getTabAt(3).setIcon(R.drawable.em_119_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(4).setIcon(R.drawable.my_page_disabled);
                } else if (position == 3) {
                    selected = dd119FRAGMENT;
                    //noinspection ConstantConditions
                    tabs.getTabAt(0).setIcon(R.drawable.home_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(1).setIcon(R.drawable.check_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(2).setIcon(R.drawable.add_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(3).setIcon(R.drawable.em_119_active);
                    //noinspection ConstantConditions
                    tabs.getTabAt(4).setIcon(R.drawable.my_page_disabled);
                } else if (position == 4) {
                    selected = myPageFragment;
                    //noinspection ConstantConditions
                    tabs.getTabAt(0).setIcon(R.drawable.home_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(1).setIcon(R.drawable.check_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(2).setIcon(R.drawable.add_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(3).setIcon(R.drawable.em_119_disabled);
                    //noinspection ConstantConditions
                    tabs.getTabAt(4).setIcon(R.drawable.my_page_active);
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container,selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
