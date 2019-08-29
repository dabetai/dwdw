package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ChatMessageAdapter chatMessageAdapter;
    public static String userName;
    public static boolean tag = true;

    public static final int CODE_REPLY = -1;
    public static final int CODE_DEFAULT = 0;
    public static final int CODE_NAME = 1;
    public static final int CODE_SCHOOL = 2;
    public static final int CODE_MAIL = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        userName = intent.getStringExtra(FrontActivity.EXTRA_MESSAGE);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_bubble, menu);

        chatMessageAdapter = new ChatMessageAdapter(this.getApplicationContext(), R.layout.chatting_message);
        final ListView listView = (ListView)findViewById(R.id.listView1);
        listView.setAdapter(chatMessageAdapter);
        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        chatMessageAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(chatMessageAdapter.getCount() - 1);
            }
        });
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, Guideline.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void send(View view){
        EditText editText = (EditText) findViewById(R.id.editText2);
        String message = editText.getText().toString();
        if(message.matches("")) {
            return;
        }
        ChatMessage chatMessage = new ChatMessage(message);
        chatMessageAdapter.add(chatMessage);
        reply(chatMessage);
        editText.setText("");
    }

    public void reply(ChatMessage chatMessage) {
        ChatMessage chatReply;
        tag = false;
        if(chatMessage.code == CODE_NAME){
            chatReply = new ChatMessage("상민봇:\n제 이름은 윤상민입니다.");
        } else if(chatMessage.code == CODE_SCHOOL) {
            chatReply = new ChatMessage("상민봇:\n저는 서울대학교 컴퓨터공학부 18학번입니다.");
        } else if(chatMessage.code == CODE_MAIL) {
            chatReply = new ChatMessage("상민봇:\n제 이메일은 dabetai@snu.ac.kr 입니다.");
        } else {
            return;
        }
        tag = true;
        chatReply.code = CODE_REPLY;
        chatMessageAdapter.add(chatReply);
        return;
    }
}
