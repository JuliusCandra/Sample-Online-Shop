package com.dev_candra.onlineshopproject.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class Produk_Image_Adapter extends PagerAdapter {

    private ArrayList<Integer>produkImage;
    private Context context;

    public Produk_Image_Adapter(Context context,ArrayList<Integer> produkImage){
        this.context = context;
        this.produkImage = produkImage;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView productImage1 = new ImageView(container.getContext());
        productImage1.setImageResource(produkImage.get(position));
        container.addView(productImage1);
        return productImage1;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object);
    }

    @Override
    public int getCount() {
        return produkImage.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
