package com.example.acer.login.Profile_Tab.Home_Related;


public class TogetherItem {
    private String name;
    private String content;
    private int resId;
    private int together, comment;
    private int writing_no;


    public TogetherItem(int writing_no,String name, String content, int resId, int together, int comment) {
        this.writing_no = writing_no;
        this.name = name;
        this.content = content;
        this.resId = resId;
        this.together = together;
        this.comment = comment;
    }
    public void setWriting_no(int writing_no){
        this.writing_no = writing_no;
    }
    public int getWriting_no(){
        return this.writing_no;
    }

    public int getTogether() {
        return together;
    }

    public void setTogether(int together) {
        this.together = together;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    @Override
    public String toString() {
        return "TogetherItem{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", resId=" + resId +
                ", together=" + together +
                ", comment=" + comment +
                '}';
    }
}
