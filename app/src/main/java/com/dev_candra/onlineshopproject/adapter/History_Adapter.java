package com.dev_candra.onlineshopproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.model.history_model;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.collection.LLRBNode;

import java.util.List;

public class History_Adapter extends RecyclerView.Adapter<History_Adapter.ViewHolder> {

    private List<history_model>models;
    private Context context;

    public History_Adapter(Context context, List<history_model> models){
        this.context = context;
        this.models = models;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_layout,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int src = models.get(position).getGambar_barang();
        String judul_barang = models.get(position).getJudul_barang();
        String harga_barang = models.get(position).getHarga_barang();
        String rating = models.get(position).getRating();
        String jumlah_rating = models.get(position).getJumlah_rating();
        String tanggal_pembelian = models.get(position).getTanggal_pembelian();
        String status = models.get(position).getStatus_pembelian();
        holder.setAllData(src,judul_barang,harga_barang,rating,jumlah_rating,tanggal_pembelian,status);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private MaterialTextView textjudulProduk,teksHaragbarang,teksRating,
        teksJumlahRating,tanggalPembelian,tekStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.gambar_produk1);
            textjudulProduk = itemView.findViewById(R.id.judul_produk1);
            teksHaragbarang = itemView.findViewById(R.id.harga_barang);
            teksRating = itemView.findViewById(R.id.ratng_history);
            teksJumlahRating = itemView.findViewById(R.id.jumlah_rating_anda);
            tanggalPembelian = itemView.findViewById(R.id.tanggal_pembelian_anda);
            tekStatus = itemView.findViewById(R.id.status_pemebelian);
        }

        @SuppressLint("ResourceAsColor")
        private void setAllData(int src, String teksJudul, String harga_barang, String rating, String jumlahRating, String tanggal_pemebelian, String status){
            textjudulProduk.setText(teksJudul);
            teksHaragbarang.setText(harga_barang);
            teksRating.setText(rating);
            teksJumlahRating.setText(jumlahRating);
            tanggalPembelian.setText(tanggal_pemebelian);
            imageView.setImageResource(src);
            if (status.equalsIgnoreCase("sukses")){
                tekStatus.setTextColor(Color.parseColor(String.valueOf(Color.GREEN)));
                tekStatus.setText(status);
            }else{
                tekStatus.setTextColor(Color.parseColor(String.valueOf(Color.RED)));
                tekStatus.setText(status);
            }
        }
    }
}
