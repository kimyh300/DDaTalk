package com.example.acer.login.Profile_Tab;


public class TogetherItem {
    String name;
    String content;
    int resId;
    int together, comment;


    public TogetherItem(String name, String content, int resId, int together, int comment) {
        this.name = name;
        this.content = content;
        this.resId = resId;
        this.together = together;
        this.comment = comment;
    }

    public int getTogether() {
        return together;
    }

    public void setTogether(int together) {
        this.together = together;
    }
    public void clickTogether(){
        this.together +=1;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }
    public void clickComment(){
        this.comment +=1;
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
