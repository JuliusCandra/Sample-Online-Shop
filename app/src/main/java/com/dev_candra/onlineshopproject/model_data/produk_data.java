package com.dev_candra.onlineshopproject.model_data;

import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.model.produk_model;

import java.util.ArrayList;

public class produk_data {
   private int [] gambar_produk = {
           R.drawable.ic_facebook,
           R.drawable.ic_book,
           R.drawable.ic_whatsapp,
           R.drawable.ic_tshirt,
           R.drawable.ic_vpn_key_black_24dp,
           R.drawable.ic_user
   };

   private String [] judul_produk = {
           "Samsung Galaxy 4 S",
           "Samsung bandal",
           "Samsung mana aja",
           "Manusia",
           "Kamu",
           "Dia"
   };

   private String [] deskripsi_produk = {
           "Produk aman",
           "Produk terjangkau",
           "Produk mantap",
           "Kamu",
           "Dimana",
           "Siapa"
   };

   private String [] harga_produk = {
           "Rp 20000",
           "Rp 30000",
           "Rp 50000",
           "Rp 50000",
           "Rp 80000",
           "Rp 100000"
   };


   public ArrayList<produk_model> getDataProdukModel(){
       ArrayList<produk_model> models = new ArrayList<>();
       for (int position = 0; position < judul_produk.length; position++ ){
           produk_model model = new produk_model();
           model.setGambar_prouduk(gambar_produk[position]);
           model.setNama_produk(judul_produk[position]);
           model.setProsesor_produk(deskripsi_produk[position]);
           model.setHarga_produk(harga_produk[position]);
           models.add(model);
       }
       return models;
   }
}
