package com.example.acer.login.Profile_Tab.Home_Related;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.acer.login.R;

import java.util.ArrayList;


public class TogetherItemAdapter extends BaseAdapter {

    ArrayList<TogetherItem> items = new ArrayList<TogetherItem>();

    @Override
    public int getCount() {
        return items.size();
    }

    public void addItem(TogetherItem item){
        items.add(item);
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        TogetherItemView view = null;
        if(convertView == null){
            view = new TogetherItemView(context);
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.together_item, parent, false);
        }else{
            view = (TogetherItemView) convertView;
        }
        final TogetherItemView finalView = view;
        final TogetherItem item = items.get(position);
        Button together_button = (Button)view.findViewById(R.id.together_button);
        together_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.clickTogether();
                finalView.setTogether_tv(item.getTogether());
                Toast.makeText(context,"함께타요 버튼이 눌렸습니다.",Toast.LENGTH_SHORT).show();
            }
        });
        Button comment_button = (Button)view.findViewById(R.id.comment_button);
        comment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.clickComment();
                finalView.setComment_Tv(item.getComment());
                Toast.makeText(context,"댓글달기 버튼이 눌렸습니다.",Toast.LENGTH_SHORT).show();
            }
        });

        view.setName(item.getName());
        view.setContent(item.getContent());
        view.setImageView(item.getResId());
        view.setTogether_tv(item.getTogether());
        view.setComment_Tv(item.getComment());

        return view;

    }


}
