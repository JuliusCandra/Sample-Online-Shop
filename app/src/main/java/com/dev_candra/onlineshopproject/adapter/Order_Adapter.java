package com.dev_candra.onlineshopproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;

import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.activity.Activity_Order_Detail;
import com.dev_candra.onlineshopproject.model.order_model;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import static com.dev_candra.onlineshopproject.R.color.colorTeksReset;

public class Order_Adapter extends RecyclerView.Adapter<Order_Adapter.ViewHolder> {

    private List<order_model> order_modelList;
    private Context context;

    public Order_Adapter(Context context, List<order_model> models){
        this.context = context;
        this.order_modelList = models;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        order_model model = order_modelList.get(position);
        String tittle = model.getProduct_tittle();
        int gambar = model.getProduct_image();
        String status_pembelian = model.getDevelivery_status();
        int rating = model.getRating();
        holder.setAllData(gambar,tittle,status_pembelian,rating);
    }

    @Override
    public int getItemCount() {
        return order_modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView gambarProduk;
        private MaterialTextView judul_produk;
        private MaterialTextView status_pembelian;
        private ImageView gambarIndicator;
        private LinearLayout rating_layout;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            gambarProduk = itemView.findViewById(R.id.gambar);
            status_pembelian = itemView.findViewById(R.id.tanggal_pembelian);
            judul_produk = itemView.findViewById(R.id.judul_gambar);
            gambarIndicator = itemView.findViewById(R.id.gambar_dot);
            rating_layout = itemView.findViewById(R.id.rating_rating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent order_DetailActivity = new Intent(itemView.getContext(), Activity_Order_Detail.class);
                    itemView.getContext().startActivity(order_DetailActivity);
                }
            });
        }
        @SuppressLint("ResourceType")
        private void setAllData(int resource, String judul_gambar, String status_pembelian1,int rating){
            if (status_pembelian1.equalsIgnoreCase("Cancel")){
                gambarIndicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.colorPrimaryDark)));
            }else{
                gambarIndicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.colorTeksReset)));
            }
            gambarProduk.setImageResource(resource);
            judul_produk.setText(judul_gambar);
            status_pembelian.setText(status_pembelian1);

            /// START Rating Layout
            setRating(rating);
            // Melakukan loopign pada child linear layout
            for (int i = 0;  i < rating_layout.getChildCount(); i++){
                final int positon = i;
                rating_layout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setRating(positon);
                    }
                });
            }
        }
        // method yang digunakan untuk membuat rating pada layout
        private void setRating(int pos){
            for (int i = 0; i < rating_layout.getChildCount(); i++){
                ImageView ratingBtn = (ImageView)rating_layout.getChildAt(i);
                ratingBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
                if (i <= pos){
                    ratingBtn.setImageTintList(ColorStateList.valueOf(Color.RED));
                }
            }
        }
        /// END RATING LAYOUT
    }
}
