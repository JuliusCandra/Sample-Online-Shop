package com.dev_candra.onlineshopproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.model.produk_spefication_model;
import com.google.android.material.textview.MaterialTextView;
import com.google.protobuf.Internal;

import java.lang.reflect.TypeVariable;
import java.util.List;

public class produk_spefication_adapter extends RecyclerView.Adapter<produk_spefication_adapter.ViewHolder> {

    private Context context;
    private List<produk_spefication_model> modelList;

    public produk_spefication_adapter(Context context, List<produk_spefication_model> modelList1) {
        this.context = context;
        this.modelList = modelList1;

    }

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){
            case produk_spefication_model.SPEFICATION_TITTLE:
                MaterialTextView teksTittle = new MaterialTextView(parent.getContext());
                teksTittle.setTypeface(null,Typeface.BOLD);
                teksTittle.setTextColor(Color.BLACK);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(setUp(16,parent.getContext()),setUp(16,parent.getContext()),setUp(16,parent.getContext()),setUp(8,parent.getContext()));
                teksTittle.setLayoutParams(layoutParams);
                return new ViewHolder(teksTittle);
            case produk_spefication_model.SPEFICATION_BODY:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_produk, parent, false)
                );
            default:
                return null;
        }

    }

    @Override
    public int getItemViewType(int position) {
        switch (modelList.get(position).getType()){
            case 0:
                return produk_spefication_model.SPEFICATION_TITTLE;
            case 1:
                return produk_spefication_model.SPEFICATION_BODY;
            default:
                return -1;

        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        switch (modelList.get(position).getType()){
            case produk_spefication_model.SPEFICATION_TITTLE:
                String tittle = modelList.get(position).getTittle();
                holder.setTeksTittle(tittle);
                break;
            case produk_spefication_model.SPEFICATION_BODY:
                String featurename = modelList.get(position).getFeatureName();
                String featureValue = modelList.get(position).getFeatureValue();
                holder.setFeatuerName(featurename,featureValue);
                break;
            default:
                return;
        }

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialTextView teksTittle;
        TextView textFeature,textValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textFeature = itemView.findViewById(R.id.teks_feature_name);
            textValue = itemView.findViewById(R.id.teks_feature_value);
        }

        void setTeksTittle(String tittleAnda){
            teksTittle = (MaterialTextView)itemView;
            teksTittle.setText(tittleAnda);
        }

        void setFeatuerName(String featuerName,String featurevalue){
            textFeature.setText(featuerName);
            textValue.setText(featurevalue);
        }
    }

    private int setUp(int dp,Context context){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources().getDisplayMetrics());

    }
}
