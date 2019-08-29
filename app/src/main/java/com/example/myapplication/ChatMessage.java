package com.example.myapplication;

public class ChatMessage {
    public String message;
    public int code;
    public boolean tag;
    public static final int CODE_REPLY = -1;
    public static final int CODE_DEFAULT = 0;
    public static final int CODE_NAME = 1;
    public static final int CODE_SCHOOL = 2;
    public static final int CODE_MAIL = 3;

    public ChatMessage(String message) {
        super();
        this.message = message;
        code = CODE_DEFAULT;
        validationCheck();
        if(MainActivity.tag) {
            this.message = MainActivity.userName + ":\n" + this.message;
        }
    }

    public void validationCheck() {
        if(message.contains("이름")){
            code = CODE_NAME;
        } else if(message.contains("학교")) {
            code = CODE_SCHOOL;
        } else if(message.contains("메일")) {
            code = CODE_MAIL;
        }
    }
}