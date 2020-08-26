package com.dev_candra.onlineshopproject.model_data;

import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.model.model_category;

import java.util.ArrayList;

public class categoy_data {

    private String [] categoryNama = {
            "Home",
            "Elektronic",
            "Clothes",
            "Furniture",
            "Shoes",
            "Sports",
            "Toys",
            "Books"
    };


    private int [] gambar = {
            R.drawable.ic_home_black_24dp,
            R.drawable.ic_phone,
            R.drawable.ic_tshirt,
            R.drawable.ic_bed,
            R.drawable.ic_shoes,
            R.drawable.ic_shopping_basket_black_24dp,
            R.drawable.ic_toys,
            R.drawable.ic_book
    };


    public ArrayList<model_category> getlist(){
        ArrayList<model_category>categories = new ArrayList<>();
        for (int position = 0; position < categoryNama.length; position++){
            model_category model = new model_category();
            model.setCategoryName(categoryNama[position]);
            model.setCategoryLogo(gambar[position]);
            categories.add(model);
        }
        return categories;
    }

}
