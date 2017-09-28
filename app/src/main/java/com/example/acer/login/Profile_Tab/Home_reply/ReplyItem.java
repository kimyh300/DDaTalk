package com.example.acer.login.Profile_Tab.Home_reply;



public class ReplyItem {

//    private int resId;
    private String email;
    private String content;
    private int reply_no;

    public ReplyItem(/*int resId,*/ int reply_no,String email, String content) {
//        this.resId = resId;
        this.reply_no = reply_no;
        this.email = email;
        this.content = content;
    }
    public ReplyItem(String email, String content){
        this.email = email;
        this.content = content;
    }

   /* public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }*/

    public int getReply_no() {
        return reply_no;
    }

    public void setReply_no(int reply_no) {
        this.reply_no = reply_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
