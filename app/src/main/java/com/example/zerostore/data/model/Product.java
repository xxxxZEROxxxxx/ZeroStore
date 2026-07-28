package com.example.zerostore.data.model;

import com.google.gson.annotations.SerializedName;

public class Product {

    // Table name
    public static final String TABLE = "products";

    // Column names
    public static final String COL_ID = "id";
    public static final String COL_CATEGORY_ID = "category_id";
    public static final String COL_NAME_AR = "name_ar";
    public static final String COL_PRICE = "price";
    public static final String COL_CURRENCY = "currency";
    public static final String COL_DURATION = "duration";
    public static final String COL_DELIVERY_TIME = "delivery_time";
    public static final String COL_WARRANTY = "warranty";
    public static final String COL_REQUIREMENTS = "requirements";
    public static final String COL_DESCRIPTION_AR = "description_ar";
    public static final String COL_NOTES_AR = "notes_ar";
    public static final String COL_IS_TOP = "is_top";
    public static final String COL_IS_OFFER = "is_offer";

    // CREATE TABLE statement
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE + " ("
            + COL_ID + " INTEGER PRIMARY KEY, "
            + COL_CATEGORY_ID + " INTEGER, "
            + COL_NAME_AR + " TEXT NOT NULL, "
            + COL_PRICE + " TEXT, "
            + COL_CURRENCY + " TEXT, "
            + COL_DURATION + " TEXT, "
            + COL_DELIVERY_TIME + " TEXT, "
            + COL_WARRANTY + " TEXT, "
            + COL_REQUIREMENTS + " TEXT, "
            + COL_DESCRIPTION_AR + " TEXT, "
            + COL_NOTES_AR + " TEXT, "
            + COL_IS_TOP + " INTEGER DEFAULT 0, "
            + COL_IS_OFFER + " INTEGER DEFAULT 0"
            + ");";

    @SerializedName("id")
    private int id;

    @SerializedName("category_id")
    private int categoryId;

    @SerializedName("name_ar")
    private String nameAr;

    @SerializedName("price")
    private String price;

    @SerializedName("currency")
    private String currency;

    @SerializedName("duration")
    private String duration;

    @SerializedName("delivery_time")
    private String deliveryTime;

    @SerializedName("warranty")
    private String warranty;

    @SerializedName("requirements")
    private String requirements;

    @SerializedName("description_ar")
    private String descriptionAr;

    @SerializedName("notes_ar")
    private String notesAr;

    @SerializedName("is_top")
    private boolean isTop;

    @SerializedName("is_offer")
    private boolean isOffer;

    public Product() {}

    public Product(int id, int categoryId, String nameAr, String price, String currency,
            String duration, String deliveryTime, String warranty,
            String requirements, String descriptionAr, String notesAr,
            boolean isTop, boolean isOffer) {
        this.id = id;
        this.categoryId = categoryId;
        this.nameAr = nameAr;
        this.price = price;
        this.currency = currency;
        this.duration = duration;
        this.deliveryTime = deliveryTime;
        this.warranty = warranty;
        this.requirements = requirements;
        this.descriptionAr = descriptionAr;
        this.notesAr = notesAr;
        this.isTop = isTop;
        this.isOffer = isOffer;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public String getNameAr() { return nameAr; }
    public void setNameAr(String nameAr) { this.nameAr = nameAr; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public String getDeliveryTime() { return deliveryTime; }
    public void setDeliveryTime(String deliveryTime) { this.deliveryTime = deliveryTime; }

    public String getWarranty() { return warranty; }
    public void setWarranty(String warranty) { this.warranty = warranty; }

    public String getRequirements() { return requirements; }
    public void setRequirements(String requirements) { this.requirements = requirements; }

    public String getDescriptionAr() { return descriptionAr; }
    public void setDescriptionAr(String descriptionAr) { this.descriptionAr = descriptionAr; }

    public String getNotesAr() { return notesAr; }
    public void setNotesAr(String notesAr) { this.notesAr = notesAr; }

    public boolean isTop() { return isTop; }
    public void setTop(boolean isTop) { this.isTop = isTop; }

    public boolean isOffer() { return isOffer; }
    public void setOffer(boolean isOffer) { this.isOffer = isOffer; }
}
