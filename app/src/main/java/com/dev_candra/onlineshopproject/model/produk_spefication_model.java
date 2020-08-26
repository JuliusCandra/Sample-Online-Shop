package com.dev_candra.onlineshopproject.model;

public class produk_spefication_model {

    public static final int SPEFICATION_TITTLE = 0;
    public static final int SPEFICATION_BODY = 1;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    /// Spefication Tittle
    private String tittle;

    public produk_spefication_model(int type, String tittle) {
        this.type = type;
        this.tittle = tittle;
    }

    public produk_spefication_model(String tittle) {
        this.tittle = tittle;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
/// Spefication Tittle

    /// Spefication Body
    private String featureName;
    private String featureValue;
    /// Spefication Body

    public produk_spefication_model(int type,String featureName, String featureValue) {
        this.featureName = featureName;
        this.featureValue = featureValue;
        this.type = type;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getFeatureValue() {
        return featureValue;
    }

    public void setFeatureValue(String featureValue) {
        this.featureValue = featureValue;
    }
}
