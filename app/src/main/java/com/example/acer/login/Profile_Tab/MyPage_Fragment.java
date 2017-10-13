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

import com.android.volley.AuthFailureError;
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
import java.util.Map;

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


        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();

                        LayoutInflater inflater = LayoutInflater.from(getContext());
                        View listViewItem = inflater.inflate(R.layout.list_items, null, true);

                        try {
                            JSONObject obj = new JSONObject(response);

                            JSONArray writingArray = obj.getJSONArray("webnautes");

                            if(writingArray.get(0) != "") {
                                mimageButton.setVisibility(View.GONE);
                            }

                            for (int i = 0; i < writingArray.length(); i++) {

                                JSONObject writingObject = writingArray.getJSONObject(i);
                                DateFormat trans = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                                String content = writingObject.getString("content");
                                String with_cnt = writingObject.getString("with_cnt");
                                String date = writingObject.getString("date");

                                Date tr1 = trans.parse(date);
                                date = format.format(tr1);

                                HashMap<String,String> hashMap = new HashMap<>();

                                hashMap.put("content", content);
                                hashMap.put("with_cnt", with_cnt);
                                hashMap.put("date", date);


                                try{ mArrayList.add(hashMap);}
                                catch (Exception e){e.printStackTrace(); Toast.makeText(rootView.getContext(), e.toString(), Toast.LENGTH_LONG).show();}

                            }

                            ListAdapter adapter = new SimpleAdapter(
                                    getActivity().getApplication(), mArrayList, R.layout.list_items,
                                    new String[]{"content","with_cnt", "date"},
                                    new int[]{R.id.textViewContent, R.id.textViewWith_cnt, R.id.textViewDate}
                            );

                            listView.setAdapter(adapter);


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
                }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email",useremail);
                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplication());
        requestQueue.add(stringRequest);


        return rootView;
    }

}
