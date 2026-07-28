package com.example.zerostore.data.model;

public class Favorite {

    // Table name
    public static final String TABLE = "favorites";

    // Column names
    public static final String COL_PRODUCT_ID = "productId";

    // CREATE TABLE statement
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ("
            + COL_PRODUCT_ID + " INTEGER PRIMARY KEY"
            + ");";

    private int productId;

    public Favorite() {}

    public Favorite(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
