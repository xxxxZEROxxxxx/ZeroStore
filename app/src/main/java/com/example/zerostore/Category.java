package com.example.zerostore;

public class Category {
    private int id;
    private String nameAr;
    private String icon;

    public Category(int id, String nameAr, String icon) {
        this.id = id;
        this.nameAr = nameAr;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public String getNameAr() {
        return nameAr;
    }

    public String getIcon() {
        return icon;
    }
}
