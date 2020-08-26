package com.dev_candra.onlineshopproject.model_data;

import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.model.slider_model;

import java.util.ArrayList;
import java.util.List;

public class slider_data {

    private int [] dataBanner = {
            R.drawable.gambar_slider,
            R.drawable.gambar_slider_one,
            R.drawable.gambar_slider_two,
            R.drawable.ic_tshirt,
            R.drawable.ic_toys,
            R.drawable.ic_user,
            R.drawable.ic_whatsapp
    };

    private String [] dataBackground = {
        "#077AE4",
        "#077AE4",
        "#077AE4",
        "#077AE4",
        "#077AE4",
            "#077AE4",
            "#077AE4"
    };

    public List<slider_model> getData(){
        List<slider_model> slider_data1 = new ArrayList<>();
        for (int position = 0; position < dataBackground.length; position++){
            slider_model model = new slider_model();
            model.setBanner(dataBanner[position]);
            model.setBackgroundBanner(dataBackground[position]);
            slider_data1.add(model);
        }
        return slider_data1;
    }

}
