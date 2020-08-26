package com.dev_candra.onlineshopproject.model;

import java.util.ArrayList;
import java.util.List;

public class home_page_model {

    // Menginisasi banner slider
    public static final int BannerSlider = 0;
    // Menginisiasi strip adsense banner
    public static final int STRIP_AD_BANNER = 1;
    public static final int HORIZONTAL_PRODUCT_VIEW = 2;
    public static final int GRID_PRODUCT_VIEW = 3;
    // menginisasi type
    private int type;
    // menginisiasi arraylist
    private List<slider_model> models;

    // membuat constructor
    public home_page_model(int type, List<slider_model> models) {
        this.type = type;
        this.models = models;
    }

    /// START BANNER
    // START membuat getter and setter BANNER
    public int getType() {
        return type;
    }

    public List<slider_model> getModels() {
        return models;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setModels(List<slider_model> models) {
        this.models = models;
    }
    // END membuat getter and setter BANNER
    /// END BANNER

    /// START STRIP ADSENSE
    // menginisiasi resource padda adsense banner
    private int resource;
    // menginisasi background pada adsense banner
    private String background;

    // membuat constructor untuk strip adsense
    public home_page_model(int type,int resource, String background) {
        this.type = type;
        this.resource = resource;
        this.background = background;
    }

    // START getter and setter StripBanner
    public int getResource() {
        return resource;
    }

    public String getBackground() {
        return background;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public void setBackground(String background) {
        this.background = background;
    }
    // END getter and setter StripBanner
    /// END STRIP ADSENSE

    //// START HORIZONTAL_LAYOUT_PRODUK && START GRID PRODUK
    private String tittle;
    private ArrayList<produk_model> produk_models;

    public home_page_model(int type,String tittle, ArrayList<produk_model> produk_models) {
        this.type = type;
        this.tittle = tittle;
        this.produk_models = produk_models;
    }

    public String getTittle() {
        return tittle;
    }

    public ArrayList<produk_model> getProduk_models() {
        return produk_models;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setProduk_models(ArrayList<produk_model> produk_models) {
        this.produk_models = produk_models;
    }

    /// END HORIZONTAL_LAYOUT_PRODUK && END GRID LAYOUT PRODUK
}
