package com.example.acer.login.Profile_Tab;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.login.Login_Related.Constants;
import com.example.acer.login.Profile_Tab.Home_Related.TogetherItem;
import com.example.acer.login.Profile_Tab.Home_Related.TogetherItemAdapter;
import com.example.acer.login.Profile_Tab.Home_Related.Writing;
import com.example.acer.login.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Home_Fragment extends Fragment{

    RequestQueue rq;
    String content,date,email;
    int reply_cnt,with_cnt,writing_no;
    final TogetherItemAdapter adapter = new TogetherItemAdapter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_home,container,false);

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity().getApplication(), "Refresh success", Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        ListView together2 = (ListView) rootView.findViewById(R.id.together2);

        rq = Volley.newRequestQueue(rootView.getContext());


        sendJsonRequestToWriting();
        together2.setAdapter(adapter);


        return rootView;
    }


    public void sendJsonRequestToWriting(){

        final JsonArrayRequest jsonArrayRequest;
        jsonArrayRequest = new JsonArrayRequest(Constants.URL_WRITING_INFO, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i<response.length();i++){
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        content = obj.getString("content");
                        date =obj.getString("date");
                        email = obj.getString("email");
                        reply_cnt = obj.getInt("reply_cnt");
                        with_cnt = obj.getInt("with_cnt");
                        writing_no = obj.getInt("writing_no");
                        Writing w = new Writing(content,reply_cnt,with_cnt,date,writing_no,email);
                        TogetherItem togetherItem = new TogetherItem(w.getWriting_no(),w.getEmail(),w.getContent(),R.drawable.user,w.getWith_cnt(),w.getReply_cnt());
                        adapter.addItem(togetherItem);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        rq.add(jsonArrayRequest);
    }


}
