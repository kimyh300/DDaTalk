package com.example.acer.login.Profile_Tab;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.login.Profile_Tab.Write_Related.FindSpot_Fragment;
import com.example.acer.login.R;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class Write_Fragment extends Fragment{

    ViewGroup rootView;
    //생명주기 확인용 태그
    private String TAG = "ActivityLifeCycle";

    //변수 선언
    EditText Content;
    TextView spot, gu;

    ImageButton x_mark;

    String ContentHolder, Rental_spot_Holder, receive_spot, receive_gu;

    ProgressDialog progressDialog;

    RequestQueue requestQueue;

    String HttpUrl = "http://104.198.211.126/insertWriting.php";

    // SharedPreferences 선언
    SharedPreferences pref;
    // SharedPreferences 에디터 선언
    SharedPreferences.Editor editor;


    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            receive_spot = getArguments().getString("rental_spot");
            receive_gu = getArguments().getString("gu_selected");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.fragment_write,container,false);

        //x버튼 클릭시 이벤트 처리 만들어놈
        x_mark = (ImageButton)rootView.findViewById(R.id.x_Button);

        x_mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new Write_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.remove(newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        //데이터 받아오기

        spot = (TextView)rootView.findViewById(R.id.SpotView);
        gu = (TextView)rootView.findViewById(R.id.guView);

        // Volley 준비 작업

        Content = (EditText)rootView.findViewById(R.id.writing_sec);

        ImageButton Insert_writing = (ImageButton)rootView.findViewById(R.id.v_Button);

        requestQueue = Volley.newRequestQueue(rootView.getContext());
        progressDialog = new ProgressDialog(rootView.getContext());
        ImageButton search = (ImageButton)rootView.findViewById(R.id.search_btn);


        // 돋보기 버튼 클릭시 장소찾기 화면 가기

        search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Create new fragment and transaction
               Fragment spotFragment = new FindSpot_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, spotFragment);
                transaction.addToBackStack(null);

                transaction.commit();

            }
        });




        // v_mark 클릭시 이벤트
        Insert_writing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Showing progress dialog at user registration time.
                progressDialog.setMessage("Please Wait, We are Inserting Your Data on Server");
                progressDialog.show();

                // Calling method to get value from EditText.
                GetValueFromEditText();

                // Creating string request with post method.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {

                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                // Showing response message coming from server.
                                Toast.makeText(rootView.getContext(), ServerResponse, Toast.LENGTH_LONG).show();

                                //전송다하고 값 초기화
                                Content.setText("");
                                spot.setText("");
                                gu.setText("");
                                pref = getActivity().getSharedPreferences("content", MODE_PRIVATE);
                                editor = pref.edit();
                                editor.remove("content");
                                editor.commit();


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                                // Hiding the progress dialog after all task complete.
                                progressDialog.dismiss();

                                // Showing error message if something goes wrong.
                                Toast.makeText(rootView.getContext(), volleyError.toString(), Toast.LENGTH_LONG).show();
                            }
                        })

                {
                    @Override
                    protected Map<String, String> getParams() {

                        // Creating Map String Params.
                        Map<String, String> params = new HashMap<String, String>();

                        // Adding All values to Params.
                        params.put("content", ContentHolder);
                        params.put("rental_spot", Rental_spot_Holder);

                        return params;
                    }

                };


                // Adding the StringRequest object into requestQueue.
                requestQueue.add(stringRequest);


            }

        });

        Log.i(TAG, "onCreate()");
        return rootView;
    }

    //x버튼 클릭시 종료



    // 벨류값 뽑아내기 메소드
    public void GetValueFromEditText(){

        ContentHolder = Content.getText().toString().trim();

        Rental_spot_Holder = spot.getText().toString().trim();
    }


    @Override
    public void onStart(){
        super.onStart();


        Log.i(TAG, "onStart()");
    }

    @Override
    public void onStop(){
        super.onStop();


        Log.i(TAG, "onStop()");
    }

    @Override
    public void onResume(){
        super.onResume();

        //지역구,대여소 번들 받아오기

        spot.setText(receive_spot);
        gu.setText(receive_gu);

        //저장된 shared 부르기
        pref = getActivity().getSharedPreferences("content", MODE_PRIVATE);
        String call = pref.getString("content","");
        Content.setText(call);


        Log.i(TAG, "onResume()");
    }


    @Override
    public void onPause(){
        super.onPause();

        pref = getActivity().getSharedPreferences("content",MODE_PRIVATE);
        editor = pref.edit();
        String str = Content.getText().toString(); // 사용자가 입력한 값
        // Activity 가 종료되기 전에 저장한다
        editor.putString("content", str); // 입력
        editor.commit(); // 파일에 최종 반영함


        Log.i(TAG, "onPause()");


    }



}

