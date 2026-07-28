package com.example.zerostore.data.model;

import com.google.gson.annotations.SerializedName;

public class Category {

    // Table name
    public static final String TABLE = "categories";

    // Column names
    public static final String COL_ID = "id";
    public static final String COL_NAME_AR = "name_ar";
    public static final String COL_ICON = "icon";

    // CREATE TABLE statement
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ("
            + COL_ID + " INTEGER PRIMARY KEY, "
            + COL_NAME_AR + " TEXT NOT NULL, "
            + COL_ICON + " TEXT"
            + ");";

    @SerializedName("id")
    private int id;

    @SerializedName("name_ar")
    private String nameAr;

    @SerializedName("icon")
    private String icon;

    public Category() {}

    public Category(int id, String nameAr, String icon) {
        this.id = id;
        this.nameAr = nameAr;
        this.icon = icon;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNameAr() { return nameAr; }
    public void setNameAr(String nameAr) { this.nameAr = nameAr; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
}
