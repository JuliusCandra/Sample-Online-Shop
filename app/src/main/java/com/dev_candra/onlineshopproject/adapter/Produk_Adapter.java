package com.dev_candra.onlineshopproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.activity.Produk_Detail_Activity;
import com.dev_candra.onlineshopproject.model.produk_model;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class Produk_Adapter extends RecyclerView.Adapter<Produk_Adapter.ViewHolder> {

    private Context context;
    private ArrayList<produk_model> models;

    public Produk_Adapter(Context context,ArrayList<produk_model> models){
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.produk,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(models.get(position).getGambar_prouduk())
                .apply(new RequestOptions().override(75,75))
                .into(holder.gambar_produk1);
        holder.textTittle_produk.setText(models.get(position).getNama_produk());
        holder.textDescription.setText(models.get(position).getProsesor_produk());
        holder.textPrice.setText(models.get(position).getHarga_produk());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent produkDetail = new Intent(context,Produk_Detail_Activity.class);
                context.startActivity(produkDetail);

            }
        });

    }

    @Override
    public int getItemCount() {

        if (models.size() > 8){
            return 8;
        }else{
            return models.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView gambar_produk1;
        MaterialTextView textTittle_produk,textPrice,textDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gambar_produk1 = itemView.findViewById(R.id.gambar_produk);
            textTittle_produk = itemView.findViewById(R.id.teks_produk);
            textPrice = itemView.findViewById(R.id.teks_harga);
            textDescription = itemView.findViewById(R.id.teks_prosesor);
        }
    }
}
