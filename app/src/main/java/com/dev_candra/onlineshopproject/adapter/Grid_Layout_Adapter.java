package com.dev_candra.onlineshopproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.activity.Produk_Detail_Activity;
import com.dev_candra.onlineshopproject.model.produk_model;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class Grid_Layout_Adapter extends BaseAdapter {

    private ArrayList<produk_model> models;
    private Context context;

    public Grid_Layout_Adapter(Context context, ArrayList<produk_model> models){
        this.context = context;
        this.models = models;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view;
        if (convertView == null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.produk,null);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent produkDetailIntent = new Intent(parent.getContext(), Produk_Detail_Activity.class);
                    parent.getContext().startActivity(produkDetailIntent);
                }
            });
            ImageView imageView = view.findViewById(R.id.gambar_produk);
            MaterialTextView teksJudul = view.findViewById(R.id.teks_produk);
            MaterialTextView teksProsessor = view.findViewById(R.id.teks_prosesor);
            MaterialTextView teksHarga = view.findViewById(R.id.teks_harga);
            produk_model produk_model = models.get(position);
            imageView.setImageResource(produk_model.getGambar_prouduk());
            teksJudul.setText(produk_model.getNama_produk());
            teksProsessor.setText(produk_model.getProsesor_produk());
            teksHarga.setText(produk_model.getHarga_produk());

        }else{
            view = convertView;
        }
        return view;
    }
}
