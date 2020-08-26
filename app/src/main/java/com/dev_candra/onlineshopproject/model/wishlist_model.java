package com.dev_candra.onlineshopproject.model;

public class wishlist_model {

    private int productImage;
    private String product_title;
    private int  diskon;
    private String rating;
    private int total_rating;
    private int product_price;
    private String payment_method;


    public wishlist_model(int productImage, String product_title, int diskon, String rating, int total_rating, int product_price, String payment_method) {
        this.productImage = productImage;
        this.product_title = product_title;
        this.diskon = diskon;
        this.rating = rating;
        this.total_rating = total_rating;
        this.product_price = product_price;
        this.payment_method = payment_method;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public int getDiskon() {
        return diskon;
    }

    public void setDiskon(int diskon) {
        this.diskon = diskon;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getTotal_rating() {
        return total_rating;
    }

    public void setTotal_rating(int total_rating) {
        this.total_rating = total_rating;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
}
