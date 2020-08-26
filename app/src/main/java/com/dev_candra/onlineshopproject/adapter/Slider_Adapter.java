package com.dev_candra.onlineshopproject.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.model.slider_model;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;


public class Slider_Adapter extends SliderViewAdapter<Slider_Adapter.ViewHolder> {

    private Context context;
    private List<slider_model> modelSLider;

    public Slider_Adapter(Context context,List<slider_model> modelSLider){
        this.context = context;
        this.modelSLider = modelSLider;
    }

    public void renewItems(List<slider_model> modelSLider){
        this.modelSLider = modelSLider;
        notifyDataSetChanged();
    }

    public void deleteItems(int position){
        this.modelSLider.remove(position);
    }

    public void addItems(slider_model slider_model){
        this.modelSLider.add(slider_model);
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_slider,null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        slider_model model = modelSLider.get(position);
        Glide.with(viewHolder.itemView.getContext())
                .load(model.getBanner())
                .fitCenter()
                .into(viewHolder.iamge1);
    }

    @Override
    public int getCount() {
        return modelSLider.size();
    }

    public static class ViewHolder extends SliderViewAdapter.ViewHolder {

        private ImageView iamge1;

        public ViewHolder(View itemView) {
            super(itemView);
            iamge1 = itemView.findViewById(R.id.banner_slider1);
        }
    }
}



