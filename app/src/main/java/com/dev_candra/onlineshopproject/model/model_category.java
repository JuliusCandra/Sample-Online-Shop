package com.dev_candra.onlineshopproject.model;



public class model_category {

    private int CategoryLogo;
    private String CategoryName;

    public model_category(){
    }

    public int getCategoryLogo() {
        return CategoryLogo;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryLogo(int categoryLogo) {
        CategoryLogo = categoryLogo;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
