package com.rotomaker.oemindia;

public class CategoryList {
    private String title;
    private int imgId;
    public CategoryList(String title,int imgId) {
        this.title = title;

        this.imgId = imgId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    public int getImgId() {
        return imgId;
    }
    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
