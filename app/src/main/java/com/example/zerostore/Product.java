package com.example.zerostore;

public class Product {
    private int id;
    private int categoryId;
    private String nameAr;
    private String price;
    private String currency;
    private String duration;
    private String deliveryTime;
    private String warranty;
    private String requirements;
    private String descriptionAr;
    private String notesAr;
    private boolean isTop;
    private boolean isOffer;

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

    public int getId() {
        return id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getNameAr() {
        return nameAr;
    }

    public String getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDuration() {
        return duration;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public String getWarranty() {
        return warranty;
    }

    public String getRequirements() {
        return requirements;
    }

    public String getDescriptionAr() {
        return descriptionAr;
    }

    public String getNotesAr() {
        return notesAr;
    }

    public boolean isTop() {
        return isTop;
    }

    public boolean isOffer() {
        return isOffer;
    }
}
