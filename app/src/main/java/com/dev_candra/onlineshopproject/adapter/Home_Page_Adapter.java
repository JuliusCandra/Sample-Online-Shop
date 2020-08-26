package com.dev_candra.onlineshopproject.adapter;

import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.model.home_page_model;
import com.dev_candra.onlineshopproject.model.produk_model;
import com.dev_candra.onlineshopproject.model.slider_model;
import com.google.android.material.textview.MaterialTextView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Home_Page_Adapter extends RecyclerView.Adapter {

    private List<home_page_model> models;

    public Home_Page_Adapter(List<home_page_model> models) {
        this.models = models;
    }

    @Override
    public int getItemViewType(int position) {
        // Mencari type dari angka yang ditentukan yang di ambil dari arraylist homepagemodel
        // mengambil model berdasarkan posisi dan tipe dari indeks
        switch (models.get(position).getType()){
            case 0:
                // jika indeks ke 0
                // maka kembalikan nilai home_page_model.BannerSlider
                return home_page_model.BannerSlider;
            case 1:
                // Jika indeks ke 1
                // maka kembalikan nilai home_page_model.STRIP_AD_BANNER
                return home_page_model.STRIP_AD_BANNER;
            case 2:
                return home_page_model.HORIZONTAL_PRODUCT_VIEW;
            case 3:
                return home_page_model.GRID_PRODUCT_VIEW;
            default:
                // jika tidak ada maka kembalikan nilai -1 atau kemabali keawal
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Logic
        // mengambil berdasarkan type yang ada
        switch (viewType){
            // Jika type yang di ambil home_page_model>BannerSLider
            case home_page_model.BannerSlider:
                // maka akan keluar layout untuk Sliderbanner
                View bannerSliderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_banner_add_layout,parent,false);
                // kembalikan nilai untuk Sliderbanner
                return new BannerSliderViewHolder(bannerSliderView);
                // Jika type yang di ambil home_page_model.STRIP_AD_BANNER
                case home_page_model.STRIP_AD_BANNER:
                    // maka akan keluar layout untuk Strip Adsense
                    View stripAdsense = LayoutInflater.from(parent.getContext()).inflate(R.layout.iklan_layout,parent,false);
                    // maka kembalikan nilai untuk strip banner
                    return new StripAdsenseViewHolder(stripAdsense);
                case home_page_model.HORIZONTAL_PRODUCT_VIEW:
                    View produkView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scrool_xml,parent,false);
                    return new ProdukHorizontalViewHolder(produkView);
                case home_page_model.GRID_PRODUCT_VIEW:
                    View gridProduct = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_produk_layout,parent,false);
                    return new GridLayoutViewHolder(gridProduct);
            default:
                // jiak tidak ada dilakukkan kembalikan nilai null
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Ambil berdasarkan posisi dan berdsarkan type dari holder
        switch (models.get(position).getType()){
            // Jika diambil untuk home_page_model.BannerSlider
            case home_page_model.BannerSlider:
                // maka masukkan arraylist dari banner slider
                List<slider_model> models1 = models.get(position).getModels();
                // kembalikan method class dari class yang sudah dibuat
                ((BannerSliderViewHolder)holder).setBannerSliderViewPager(models1);
                break;
            case home_page_model.STRIP_AD_BANNER:
                // masukkan models yang diambil berdasarkan posisi dan berdasarkan resource yang diambil
                int resource = models.get(position).getResource();
                // masukkan model yang diambil berdsarkan posisi dan berdasarkan string yang diambil
                String color = models.get(position).getBackground();
                // kembalikan method class dari class yang sudah dibuat
                ((StripAdsenseViewHolder)holder).setStripBanner(resource,color);
                break;
            case home_page_model.HORIZONTAL_PRODUCT_VIEW:
                String tittle = models.get(position).getTittle();
                ArrayList<produk_model> produk_models = models.get(position).getProduk_models();
                ((ProdukHorizontalViewHolder)holder).setLayoutProdukHorizontal(produk_models,tittle);
                break;
            case home_page_model.GRID_PRODUCT_VIEW:
                String tittle1 = models.get(position).getTittle();
                ArrayList<produk_model> produk_models1= models.get(position).getProduk_models();
                ((GridLayoutViewHolder)holder).setGridView(produk_models1,tittle1);
                break;
            default:
                return;
        }
    }

    // Membuat item yang ada pada banner
    @Override
    public int getItemCount() {

        return models.size();
    }


    // membuat class banner slider
    public static class BannerSliderViewHolder extends RecyclerView.ViewHolder{
        private SliderView sliderView;

        // membuat constructor banner slider
        public BannerSliderViewHolder(@NonNull View itemView) {
            super(itemView);
            // Menginisiasi image slider
            sliderView = itemView.findViewById(R.id.imageSlider);

        }
        // membuat kesuluruhan method yang akan dipakai pada function
        private void setBannerSliderViewPager(List<slider_model> listModel){
            Slider_Adapter adapter = new Slider_Adapter(itemView.getContext(),listModel);
            sliderView.setSliderAdapter(adapter,false);
            sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
            sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
            sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
            sliderView.setIndicatorSelectedColor(Color.RED);
            sliderView.setIndicatorUnselectedColor(Color.GRAY);
            sliderView.setScrollTimeInSec(4);
            sliderView.startAutoCycle();
        }
    }
    // membuat class pada strip adsense
    public static class StripAdsenseViewHolder extends RecyclerView.ViewHolder {
            private ImageView stripGambar;
            private ConstraintLayout stripContainer;
    // memmbuat constructor pada strip adsense
        public StripAdsenseViewHolder(@NonNull View itemView) {
            super(itemView);
            stripGambar = itemView.findViewById(R.id.gambar_iklan);
            stripContainer = itemView.findViewById(R.id.iklan_layout);
        }
     // membuat adsense banner
        private void setStripBanner(int resource, String color){
            stripGambar.setImageResource(resource);
            stripContainer.setBackgroundColor(Color.parseColor(color));
        }
    }
    // membuat kelas produk
    public static class ProdukHorizontalViewHolder extends  RecyclerView.ViewHolder {
        private RecyclerView layoutProdukHorizontal;
        private MaterialTextView teksTerbaru;
        private Button btnSemua;
        public ProdukHorizontalViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutProdukHorizontal = itemView.findViewById(R.id.recyle_produk);
            teksTerbaru = itemView.findViewById(R.id.teks_terbaru);
            btnSemua = itemView.findViewById(R.id.button_teks_terbaru);
        }

        private void setLayoutProdukHorizontal(ArrayList<produk_model> models,String tittle){
            teksTerbaru.setText(tittle);
            if (models.size() > 8){
                btnSemua.setVisibility(View.VISIBLE);
            }else {
                btnSemua.setVisibility(View.GONE);
            }
            Produk_Adapter produk_adapter = new Produk_Adapter(itemView.getContext(),models);
            LinearLayoutManager manager = new LinearLayoutManager(itemView.getContext());
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            layoutProdukHorizontal.setLayoutManager(manager);
            layoutProdukHorizontal.setAdapter(produk_adapter);
            produk_adapter.notifyDataSetChanged();
        }
    }

    public static class GridLayoutViewHolder extends RecyclerView.ViewHolder {
            private MaterialTextView teksJudul;
            private Button button_judul;
            private GridView gridView;
        public GridLayoutViewHolder(@NonNull View itemView) {
            super(itemView);
            teksJudul = itemView.findViewById(R.id.teksJudul);
            button_judul = itemView.findViewById(R.id.button_judul);
            gridView = itemView.findViewById(R.id.grid_view);

        }
        private void setGridView(ArrayList<produk_model> models,String tittle){
            teksJudul.setText(tittle);
            gridView.setAdapter(new Grid_Layout_Adapter(itemView.getContext(),models));
        }
    }


}
