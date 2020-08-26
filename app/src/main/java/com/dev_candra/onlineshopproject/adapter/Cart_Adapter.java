package com.dev_candra.onlineshopproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ImageDecoder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.model.cart_model;

import java.util.List;

public class Cart_Adapter extends RecyclerView.Adapter{

    private Context context;
    private List<cart_model>cart_models;

    public Cart_Adapter(Context context,List<cart_model> modelList){
        this.context = context;
        this.cart_models = modelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (cart_models.get(position).getType()){
            case 0:
               return cart_model.CART_ITEM;
            case 1:
                return cart_model.TOTAL_AMOUNT;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case cart_model.CART_ITEM:
                View itemCart = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
               return new CartItemViewHolder(itemCart);
            case cart_model.TOTAL_AMOUNT:
                View cartAmount = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_detail_layout,parent,false);
                return new CartItemAmountViweHolder(cartAmount);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (cart_models.get(position).getType()){
            case cart_model.CART_ITEM:
                String tittle = cart_models.get(position).getProducTittle();
                int hargaSebanarnya = cart_models.get(position).getProduk_price();
                int diskon = cart_models.get(position).getDiskon();
                int gambarBarang = cart_models.get(position).getProduct_image();
                int productQuantiti = cart_models.get(position).getProductQuantity();
                ((CartItemViewHolder)holder).setDetailAll(gambarBarang,tittle,hargaSebanarnya,diskon,productQuantiti);
                break;
            case cart_model.TOTAL_AMOUNT:
                int total_items = cart_models.get(position).getTotalItems();
                int total_price_item = cart_models.get(position).getTotalPriceItem();
                int hargaDelivery = cart_models.get(position).getDeliveryPrice();
                ((CartItemAmountViweHolder)holder).setDetailCartItemAmount(total_items,total_price_item,hargaDelivery);
                break;
            default:
                return;


        }
    }

    @Override
    public int getItemCount() {
        return cart_models.size();
    }

   public static class CartItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView textTittle;
        private TextView textDiskon;
        private TextView hargaBarang_diskon;
        private TextView hargaBarang_sebenarnya;
        private TextView product_quantity;
        private ImageView gambar_diskon;
        private View price_cut_order;
        private Button removeButton;


        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.img_barang);
            textTittle = itemView.findViewById(R.id.mtv_nama_barang);
            textDiskon = itemView.findViewById(R.id.mtv_diskon);
            hargaBarang_diskon = itemView.findViewById(R.id.mtv_harga_cart);
            hargaBarang_sebenarnya = itemView.findViewById(R.id.mtv_harga_cart_sebenarnya);
            product_quantity = itemView.findViewById(R.id.jumlah_barang);
            gambar_diskon = itemView.findViewById(R.id.gambar_diskon);
            price_cut_order = itemView.findViewById(R.id.price_cut_order);
            removeButton = itemView.findViewById(R.id.btn_remove_item);
        }

        @SuppressLint("SetTextI18n")
         void setDetailAll(int resource, String tittle, int harga_sebenarnya,int Diskon,int productQuantitiy){
            productImage.setImageResource(resource);
            textTittle.setText(tittle);
            int total_semua = (harga_sebenarnya * productQuantitiy * Diskon) / 100;
            int total_harga_dibayar = (harga_sebenarnya * productQuantitiy) - total_semua;
            if (Diskon == 0){
                hargaBarang_diskon.setVisibility(View.GONE);
                price_cut_order.setVisibility(View.GONE);
                hargaBarang_sebenarnya.setText("Rp " + (harga_sebenarnya * productQuantitiy));
                gambar_diskon.setVisibility(View.GONE);
                textDiskon.setText("Rp " + 0);
            }else{
                hargaBarang_sebenarnya.setText("Rp " + (harga_sebenarnya * productQuantitiy));
                price_cut_order.setVisibility(View.VISIBLE);
                hargaBarang_diskon.setText("Rp " + total_harga_dibayar);
                textDiskon.setVisibility(View.VISIBLE);
                textDiskon.setText("Diskon " + Diskon + " % ");
                gambar_diskon.setVisibility(View.VISIBLE);
            }
            product_quantity.setText("Jumlah barang " + productQuantitiy);
        }

    }

  public static class CartItemAmountViweHolder extends RecyclerView.ViewHolder {
        private TextView namaBarang;
        private TextView total_price_item;
        private TextView hargaDelivery;
        private TextView harga_total_keseluruhan;
        private TextView  hargaDiskon;

        public CartItemAmountViweHolder(@NonNull View itemView) {
            super(itemView);
            namaBarang = itemView.findViewById(R.id.total_item);
            total_price_item = itemView.findViewById(R.id.total_price_item);
            hargaDelivery = itemView.findViewById(R.id.delivery_total);
            harga_total_keseluruhan = itemView.findViewById(R.id.harga_total);
            hargaDiskon = itemView.findViewById(R.id.total_keseluruhan);
        }

        @SuppressLint("SetTextI18n")
        void setDetailCartItemAmount(int items, int total_semua_item,int harga_delivery){
            int total = total_semua_item + harga_delivery;
            namaBarang.setText("Harga " + items + " items ");
            total_price_item.setText("Rp " + total_semua_item);
            hargaDelivery.setText("Rp " + harga_delivery);
            harga_total_keseluruhan.setText("Rp " +  total);
            hargaDiskon.setText("total belanja kamu " + total);
        }
    }

}
