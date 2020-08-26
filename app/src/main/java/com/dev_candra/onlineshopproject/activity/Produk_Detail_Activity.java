package com.dev_candra.onlineshopproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.adapter.Produk_Detail_Adapter;
import com.dev_candra.onlineshopproject.adapter.Produk_Image_Adapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Produk_Detail_Activity extends AppCompatActivity {

    private Toolbar toolbar1;
    private ViewPager pagerProdukDetail,produkDetailViewPager;
    private TabLayout tabLayoutProduk,produkDetailTabLayout;
    private Produk_Image_Adapter adapter;
    private FloatingActionButton buttonLove;
    private static boolean ADD_TO_LIST_ITEM = false;
    private LinearLayout rating_layout;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk__detail_);
        toolbar1 = findViewById(R.id.toolbar_detail);
        pagerProdukDetail = findViewById(R.id.produk_image_viewPager);
        tabLayoutProduk = findViewById(R.id.indicator_pager);
        buttonLove = findViewById(R.id.floatingActionButton2);
        rating_layout = findViewById(R.id.rate_now_container);
        produkDetailTabLayout = findViewById(R.id.produk_tab_layout);
        produkDetailViewPager = findViewById(R.id.view_pager_produk);
        setSupportActionBar(toolbar1);
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Candra Shop");
            getSupportActionBar().setSubtitle("Detail Produk");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Aksi();
    }



    private void Aksi(){
        containerPagerAdapterdanTabsLayoutDanButtonWishlist();
        tabSlayoutDetailProduk();
        ratingSystem();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemSelected = item.getItemId();

        if (itemSelected == R.id.search_menu_layout){
            return true;
        }else if (itemSelected == R.id.cart_menu_layout){
            return true;
        }else if (itemSelected == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void containerPagerAdapterdanTabsLayoutDanButtonWishlist(){
        // Start pager adapter with tabs layout and button whistlist
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.ic_bike);
        list.add(R.drawable.ic_book);
        list.add(R.drawable.ic_bed);
        // Membuat list pada adapter dan membuat adapter pada pager
        adapter = new Produk_Image_Adapter(this,list);
        pagerProdukDetail.setAdapter(adapter);
        // memasukkan tab ke pager
        tabLayoutProduk.setupWithViewPager(pagerProdukDetail,true);

        buttonLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ADD_TO_LIST_ITEM){
                    ADD_TO_LIST_ITEM = false;
                    buttonLove.setImageTintList(getResources().getColorStateList(R.color.WarnaAbuAbu));
                }else{
                    ADD_TO_LIST_ITEM = true;
                    buttonLove.setImageTintList(getResources().getColorStateList(R.color.colorPrimaryDark));
                }
            }
        });

        // End pager adapter with tabs layouts and button whistlist
    }

    // membuat fungsi dari tablayout pada produk
    private void tabSlayoutDetailProduk(){
        produkDetailViewPager.setAdapter(new Produk_Detail_Adapter(getSupportFragmentManager(),produkDetailTabLayout.getTabCount()));
        produkDetailViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(produkDetailTabLayout));
        produkDetailTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                produkDetailViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    // membuat system rating pada produk
    private void ratingSystem(){
        // Rating layout
        for (int i = 0; i < rating_layout.getChildCount(); i++){
            final  int startPosition = i;
            rating_layout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setRating(startPosition);
                }
            });
        }
        // Rating layout
    }

    // Membuat set Rating pada layout linear
    private void setRating(int positon){
        for (int i = 0; i < rating_layout.getChildCount(); i++){
            ImageView startBtn = (ImageView)rating_layout.getChildAt(i);
            startBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
            if (i <= positon){
                startBtn.setImageTintList(ColorStateList.valueOf(Color.RED));
            }
        }
    }
}
