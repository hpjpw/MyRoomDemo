package com.hpjpw.myroomdemo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BabyCap {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "img_id")
    private int news_thumb_id;
    private String news_info;
    private String news_title;
    private boolean visibility;

    public BabyCap(int news_thumb_id, String news_info, String news_title, boolean visibility) {
        this.news_thumb_id = news_thumb_id;
        this.news_info = news_info;
        this.news_title = news_title;
        this.visibility = visibility;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNews_thumb_id() {
        return news_thumb_id;
    }

    public void setNews_thumb_id(int news_thumb_id) {
        this.news_thumb_id = news_thumb_id;
    }

    public String getNews_info() {
        return news_info;
    }

    public void setNews_info(String news_info) {
        this.news_info = news_info;
    }

    public String getNews_title() {
        return news_title;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}
