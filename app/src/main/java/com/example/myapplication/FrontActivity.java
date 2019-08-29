package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FrontActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myapplication.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);
    }

    public void start(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText editUserName = (EditText) findViewById(R.id.userName);
        String userName = editUserName.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, userName);
        startActivity(intent);
    }
}
