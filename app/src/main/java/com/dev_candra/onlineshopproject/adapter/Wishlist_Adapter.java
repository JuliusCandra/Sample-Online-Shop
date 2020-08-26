package com.dev_candra.onlineshopproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.model.wishlist_model;
import com.google.android.material.textview.MaterialTextView;


import java.util.ArrayList;
import java.util.List;


public class Wishlist_Adapter extends RecyclerView.Adapter<Wishlist_Adapter.ViewHolder> {

    private List<wishlist_model> models;
    private Context context;

    public Wishlist_Adapter(Context context, List<wishlist_model>models1) {
        this.models = models1;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String tittle = models.get(position).getProduct_title();
        int gambar_produk = models.get(position).getProductImage();
        int diskon = models.get(position).getDiskon();
        int product_price = models.get(position).getProduct_price();
        String rating = models.get(position).getRating();
        int total_rating = models.get(position).getTotal_rating();
        String pembayaran = models.get(position).getPayment_method();
        holder.setAllData(gambar_produk,tittle,diskon,product_price,rating,total_rating,pembayaran);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView gambar_produk;
        private TextView product_tittle;
        private TextView product_diskon;
        private TextView product_price;
        private TextView cutted_price;
        private MaterialTextView rating;
        private TextView total_rating;
        private View cute_price;
        private ImageButton hapus;
        private ImageView gambar_diskon;
        private TextView payment_method;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gambar_produk = itemView.findViewById(R.id.imageView4);
            product_tittle = itemView.findViewById(R.id.nama_produk);
            product_diskon = itemView.findViewById(R.id.produk_diskon);
            product_price = itemView.findViewById(R.id.harga_produk_wishlisht);
            cutted_price = itemView.findViewById(R.id.cutted_price_wishllist);
            rating = itemView.findViewById(R.id.teksBintang);
            total_rating = itemView.findViewById(R.id.jumlah_rating);
            cute_price = itemView.findViewById(R.id.divider15);
            hapus = itemView.findViewById(R.id.button_remove);
            gambar_diskon = itemView.findViewById(R.id.gambar_diskon);
            payment_method = itemView.findViewById(R.id.payment_method);
        }

        @SuppressLint("SetTextI18n")
        private void setAllData(int resource, String tittle, int produk_diskon1, int produk_price, String rating1, int total_rating1,
                               String pembayaran ){

            int total_harga_semua = (produk_diskon1 * produk_price) / 100;
            int total_kelengkapan_harga = produk_price - total_harga_semua;
            gambar_produk.setImageResource(resource);
            product_tittle.setText(tittle);
            product_diskon.setText("Diskon " + produk_diskon1 + " %");
            if (produk_diskon1 == 0){
                product_price.setText("Rp " + produk_price);
                cutted_price.setVisibility(View.GONE);
                cute_price.setVisibility(View.GONE);
            }else{
                product_price.setText("Rp " + produk_price);
                cutted_price.setText("Rp " + total_kelengkapan_harga);
                cutted_price.setVisibility(View.VISIBLE);
                cute_price.setVisibility(View.VISIBLE);
            }

            rating.setText(rating1);
            total_rating.setText(total_rating1 + "rating");
            payment_method.setText(pembayaran);

            hapus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Klik Hapus", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
