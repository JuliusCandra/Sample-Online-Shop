package com.dev_candra.onlineshopproject.model;

import android.os.Parcelable;

public class cart_model{

    public static final int CART_ITEM = 0;
    public static final int TOTAL_AMOUNT = 1;

    private int type;

    private int product_image;
    private String producTittle;
    private int produk_price;
    private int productQuantity;
    private int diskon;

    public cart_model(int type, int product_image, String producTittle,  int produk_price, int productQuantity, int diskon) {
        this.type = type;
        this.product_image = product_image;
        this.producTittle = producTittle;
        this.produk_price = produk_price;
        this.productQuantity = productQuantity;
        this.diskon = diskon;
    }

    // buat getter dan setter type


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getProduct_image() {
        return product_image;
    }

    public void setProduct_image(int product_image) {
        this.product_image = product_image;
    }

    public String getProducTittle() {
        return producTittle;
    }

    public void setProducTittle(String producTittle) {
        this.producTittle = producTittle;
    }

    public int getProduk_price() {
        return produk_price;
    }

    public void setProduk_price(int produk_price) {
        this.produk_price = produk_price;
    }


    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getDiskon() {
        return diskon;
    }

    public void setDiskon(int diskon) {
        this.diskon = diskon;
    }


    // cart item
    private int totalItems;
    private int deliveryPrice;
    private int totalPriceItem;

    public cart_model(int type,int totalItems,  int deliveryPrice,int totalPriceItem) {
        this.type = type;
        this.totalItems = totalItems;
        this.deliveryPrice = deliveryPrice;
        this.totalPriceItem = totalPriceItem;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(int deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }


    public int getTotalPriceItem() {
        return totalPriceItem;
    }

    public void setTotalPriceItem(int totalPriceItem) {
        this.totalPriceItem = totalPriceItem;
    }
    // cart item
}
