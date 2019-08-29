package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatMessageAdapter extends ArrayAdapter {
    public List msgs = new ArrayList();
    public static final int CODE_REPLY = -1;
    public String userName;

    public ChatMessageAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public void add(ChatMessage object){
        msgs.add(object);
        super.add(object);
    }

    public int getCount(){
        return msgs.size();
    }

    public ChatMessage getItem(int index){
        return (ChatMessage) msgs.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        if(row == null) {
            LayoutInflater inflator = (LayoutInflater)
                    this.getContext().
                            getSystemService((Context.LAYOUT_INFLATER_SERVICE));
            row = inflator.inflate(R.layout.chatting_message, parent, false);
        }

        ChatMessage msg = (ChatMessage)msgs.get(position);

        TextView msgText = (TextView) row.findViewById(R.id.chatmessage);
        msgText.setText(msg.message);
        if(msg.code != CODE_REPLY) {
            msgText.setTextColor(Color.parseColor("#000000"));
            msgText.setGravity(Gravity.LEFT);
        } else {    //답장이면
            msgText.setTextColor(Color.parseColor("#F78181"));
            msgText.setGravity(Gravity.RIGHT);
        }

        return row;
    }

}
