package com.example.acer.login.Profile_Tab;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.login.Login_Related.SharedPrefManager;
import com.example.acer.login.Profile_Tab.Home_Related.Writing;
import com.example.acer.login.Profile_Tab.MyPage_Related.MyPage_SubActivity;
import com.example.acer.login.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MyPage_Fragment extends Fragment{

    ViewGroup rootView;

    private static final String JSON_URL = "http://104.198.211.126/getwriting.php";

    ListView listView;
    List<Writing> writingList;

    ArrayList<HashMap<String, String>> mArrayList;

    ImageView imageView;
    ImageButton mimageButton;

    TextView textView;

    ProgressDialog progressDialog;
    RequestQueue requestQueue;

    String username, userbirth, useremail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.fragment_mypage,container,false);

        listView = (ListView) rootView.findViewById(R.id.listview);

        writingList = new ArrayList<>();
        mArrayList = new ArrayList<>();

        imageView = (ImageView) rootView.findViewById(R.id.imageView4);
        mimageButton = (ImageButton) rootView.findViewById(R.id.imageButton11);

        textView = (TextView) rootView.findViewById(R.id.textView);

        requestQueue = Volley.newRequestQueue(rootView.getContext());
        progressDialog = new ProgressDialog(rootView.getContext());

        username = SharedPrefManager.getInstance(getActivity().getApplicationContext()).getUsername();
        userbirth = SharedPrefManager.getInstance(getActivity().getApplicationContext()).getUserBirthday();
        useremail = SharedPrefManager.getInstance(getActivity().getApplicationContext()).getUserEmail();

        textView.setText(username);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplication(), MyPage_SubActivity.class);
                i.putExtra("name_key",username);
                i.putExtra("birthday_key",userbirth);
                i.putExtra("email_key",useremail);
                startActivity(i);
            }
        });

        mimageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplication(), Write_Fragment.class);
                startActivity(i);
            }
        });

        progressDialog.setMessage("Please Wait, We are getting Your Data on Server");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();
                        //Toast.makeText(rootView.getContext(), response, Toast.LENGTH_LONG).show();

                        LayoutInflater inflater = LayoutInflater.from(getContext());
                        View listViewItem = inflater.inflate(R.layout.list_items, null, true);

                        try {
                            JSONObject obj = new JSONObject(response);

                            JSONArray writingArray = obj.getJSONArray("webnautes");

                            if(writingArray.get(0) != "") {
                                mimageButton.setVisibility(View.GONE);
                            }

                            //Toast.makeText(rootView.getContext(), writingArray.toString(), Toast.LENGTH_LONG).show();

                            for (int i = 0; i < writingArray.length(); i++) {

                                JSONObject writingObject = writingArray.getJSONObject(i);
                                DateFormat trans = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                                String content = writingObject.getString("content");
                                String with_cnt = writingObject.getString("with_cnt");
                                String date = writingObject.getString("date");

                                Date tr1 = trans.parse(date);
                                date = format.format(tr1);

                                //Toast.makeText(rootView.getContext(), content, Toast.LENGTH_LONG).show();

                                HashMap<String,String> hashMap = new HashMap<>();

                                hashMap.put("content", content);
                                hashMap.put("with_cnt", with_cnt);
                                hashMap.put("date", date);


                                try{ mArrayList.add(hashMap);}
                                catch (Exception e){e.printStackTrace(); Toast.makeText(rootView.getContext(), e.toString(), Toast.LENGTH_LONG).show();}


                                //Toast.makeText(rootView.getContext(), mArrayList.get(i).get("content"), Toast.LENGTH_LONG).show();

                            }

                            ListAdapter adapter = new SimpleAdapter(
                                    getActivity().getApplication(), mArrayList, R.layout.list_items,
                                    new String[]{"content","with_cnt", "date"},
                                    new int[]{R.id.textViewContent, R.id.textViewWith_cnt, R.id.textViewDate}
                            );

                            listView.setAdapter(adapter);


                            /*ListViewAdapter adapter = new ListViewAdapter(writingList, getActivity().getApplicationContext());

                            listView.setAdapter(adapter);*/

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplication());
        requestQueue.add(stringRequest);


        return rootView;
    }


    /*private void loadWritingList() {
        //getting the progressbar
        //final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //making the progressbar visible
        //progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //hiding the progressbar after completion
                            //progressBar.setVisibility(View.INVISIBLE);
                            progressDialog.dismiss();
                            Toast.makeText(rootView.getContext(), response, Toast.LENGTH_LONG).show();

                            try {
                                //getting the whole json object from the response
                                JSONObject obj = new JSONObject(response);

                                //we have the array named hero inside the object
                                //so here we are getting that json array
                                JSONArray writingArray = obj.getJSONArray("webnautes");

                                //now looping through all the elements of the json array
                                for (int i = 0; i < writingArray.length(); i++) {
                                    //getting the json object of the particular index inside the array
                                    JSONObject writingObject = writingArray.getJSONObject(i);

                                    //creating a hero object and giving them the values from json object
                                    Writing writing = new Writing(writingObject.getString("content"), writingObject.getString("with_cnt"), writingObject.getString("date"));

                                    //adding the hero to herolist
                                    writingList.add(writing);
                                }

                                //creating custom adapter object
                                ListViewAdapter adapter = new ListViewAdapter(writingList, getActivity().getApplicationContext());

                                //adding the adapter to listview
                                listView.setAdapter(adapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //displaying the error in toast if occurrs
                            Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplication());

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }*/


    /*private void getData() {
        *//*String id = editTextId.getText().toString().trim();
        if (id.equals("")) {
            Toast.makeText(this, "Please enter an id", Toast.LENGTH_LONG).show();
            return;
        }*//*
        loading = ProgressDialog.show(getActivity().getApplication(),"Please wait...","Fetching...",false,false);

        String url = Config.DATA_URL;//+editTextId.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplication(),error.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplication());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response){
        String content = "";
        String with_cnt = "";
        String date = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
            content = collegeData.getString(Config.KEY_CONTENT);
            with_cnt = collegeData.getString(Config.KEY_WITH_CNT);
            date = collegeData.getString(Config.KEY_DATE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListViewAdapter adapter = new ListViewAdapter(writingList, getActivity().getApplicationContext());

        listView.setAdapter(adapter);
    }*/


}
