package com.dev_candra.onlineshopproject.model;

public class order_model {
    private int product_image;
    private String product_tittle;
    private String develivery_status;
    private int rating;

    public order_model(int product_image, String product_tittle, String develivery_status,int rating) {
        this.product_image = product_image;
        this.product_tittle = product_tittle;
        this.develivery_status = develivery_status;
        this.rating = rating;
    }

    public int getProduct_image() {
        return product_image;
    }

    public void setProduct_image(int product_image) {
        this.product_image = product_image;
    }

    public String getProduct_tittle() {
        return product_tittle;
    }

    public void setProduct_tittle(String product_tittle) {
        this.product_tittle = product_tittle;
    }

    public String getDevelivery_status() {
        return develivery_status;
    }

    public void setDevelivery_status(String develivery_status) {
        this.develivery_status = develivery_status;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
