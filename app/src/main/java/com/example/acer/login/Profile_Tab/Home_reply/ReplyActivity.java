package com.example.acer.login.Profile_Tab.Home_reply;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.acer.login.Login_Related.Constants;
import com.example.acer.login.Login_Related.LoginActivity;
import com.example.acer.login.Login_Related.SharedPrefManager;
import com.example.acer.login.ProfileActivity;
import com.example.acer.login.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ReplyActivity extends AppCompatActivity {

    RequestQueue rq;
    ReplyItemAdapter replyItemAdapter;
    String content,date,email,writing_no_param,UserEmail_Present;
    int writing_no,reply_no,picture;
    EditText editTextReply;
    TextView textViewEmail, textViewContent;
    ImageView imageView;
    Button buttonWriteReply;
    ListView reply;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
        intent.putExtra("reply_cnt",replyItemAdapter.getCount());
        intent.putExtra("writing_no",writing_no_param);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        finish();
//        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        //noinspection ConstantConditions
        getSupportActionBar().hide();
        UserEmail_Present = SharedPrefManager.getInstance(this).getUserEmail();
        Intent intent = getIntent();

        email = String.valueOf(intent.getExtras().get("email"));
        content = String.valueOf(intent.getExtras().get("content"));
        picture = (int)intent.getExtras().get("picture");
        writing_no_param = String.valueOf(intent.getExtras().get("writing_no"));

        textViewEmail = (TextView)findViewById(R.id.textViewEmail);
        textViewContent = (TextView)findViewById(R.id.textViewContent);
        imageView = (ImageView)findViewById(R.id.imageView);
        editTextReply = (EditText)findViewById(R.id.editTextReply);
        buttonWriteReply = (Button)findViewById(R.id.buttonWriteReply);

        textViewEmail.setText(email);
        textViewContent.setText(content);
        imageView.setImageResource(picture);



        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(ReplyActivity.this, "Refresh success", Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        reply = (ListView)findViewById(R.id.reply);


        replyItemAdapter = new ReplyItemAdapter();
        rq = Volley.newRequestQueue(getApplicationContext());
        sendJsonRequestToReply(writing_no_param);
        reply.setAdapter(replyItemAdapter);


        buttonWriteReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Reply_Content = editTextReply.getText().toString();
                int param_reply_no = insertContentToReply(Reply_Content,UserEmail_Present,writing_no_param);
                int writing_no = Integer.parseInt(writing_no_param);
                ReplyItem newRow = new ReplyItem(param_reply_no,UserEmail_Present,Reply_Content,writing_no);
                replyItemAdapter.addItem(newRow);
                replyItemAdapter.notifyDataSetChanged();
            }
        });





    }
    public void sendJsonRequestToReply(final String writing_no_param){

        final StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REPLY_INFO, new Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(!response.equals("0 results")){
                    JSONArray arr = null;
                    try {
                        arr = new JSONArray(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    assert arr != null;
                    for (int i = 0; i < arr.length(); i++) {
                        try {
                            JSONObject obj = arr.getJSONObject(i);
                            content = obj.getString("content");
                            date = obj.getString("date");
                            email = obj.getString("email");
                            reply_no = obj.getInt("reply_no");
                            writing_no = obj.getInt("writing_no");
                            ReplyItem reply = new ReplyItem(reply_no,email, content,writing_no);
                            replyItemAdapter.addItem(reply);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("writing_no",writing_no_param);
                return params;
            }
        };
        rq.add(stringRequest);
    }

    public int insertContentToReply(final String Reply_Content, final String shared_email, final String writing_no_param){
        final int[] reply_no = {0};
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_REPLY_WRITECONTENT, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if(!obj.getBoolean("error")){
                        reply_no[0] = obj.getInt("reply_no");
                        Toast.makeText(getApplicationContext(),
                                "댓글이 성공적으로 작성되었습니다.",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("writing_no",writing_no_param);
                params.put("content",Reply_Content);
                params.put("email",shared_email);
                return params;
            }
        };
        rq.add(stringRequest);
        return reply_no[0];
    }
}
