package com.example.acer.login.Profile_Tab.Home_Related;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.acer.login.R;



public class TogetherItemView extends LinearLayout {

    TextView textView,textView2,together_tv,comment_tv;
    ImageView imageView;


    public TogetherItemView(Context context) {
        super(context);
        init(context);
    }

    public TogetherItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.together_item,this,true);

        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);
        imageView = (ImageView)findViewById(R.id.imageView);
        together_tv = (TextView)findViewById(R.id.together_tv);
        comment_tv = (TextView)findViewById(R.id.comment_tv);


    }
    public void setName(String name){
        textView.setText(name);
    }

    public void setContent(String content){
        textView2.setText(content);
    }

    public void setImageView(int resId){
        imageView.setImageResource(resId);
    }

    public void setTogether_tv(int together){
        together_tv.setText(String.valueOf(together));
    }

    public void setComment_Tv(int comment){
        comment_tv.setText(String.valueOf(comment));
    }


}
