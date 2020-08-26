package com.dev_candra.onlineshopproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.adapter.Home_Page_Adapter;
import com.dev_candra.onlineshopproject.model.home_page_model;
import com.dev_candra.onlineshopproject.model.model_category;
import com.dev_candra.onlineshopproject.model.produk_model;
import com.dev_candra.onlineshopproject.model.slider_model;
import com.dev_candra.onlineshopproject.model_data.categoy_data;
import com.dev_candra.onlineshopproject.model_data.produk_data;
import com.dev_candra.onlineshopproject.model_data.slider_data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Category_Activity extends AppCompatActivity {

    private List<slider_model> sliderModels;
    private ArrayList<produk_model> produkModel;
    private produk_data produkData;
    private slider_data sliderData;
    private Toolbar toolbar;
    private ArrayList<home_page_model>pageModel;
    private RecyclerView categoryRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_);
        toolbar = findViewById(R.id.toolbar_category);
        categoryRecycle = findViewById(R.id.category_recycle);
        // Menginisiasi model
        produkModel = new ArrayList<>();
        sliderModels = new ArrayList<>();
        pageModel = new ArrayList<>();
        // Menginisasi data model
        produkData = new produk_data();
        sliderData = new slider_data();
        // Menambahkan data kedalam model
        sliderModels.addAll(sliderData.getData());
        produkModel.addAll(produkData.getDataProdukModel());
        Aksi();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_icon,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemMenu = item.getItemId();
        if (itemMenu  == R.id.search){
            return true;
        }else if (itemMenu == android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void Aksi(){
        setSupportActionBar(toolbar);
        String tittle = getIntent().getStringExtra("category");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Candra Shop");
            getSupportActionBar().setSubtitle(tittle);
        }
        LinearLayoutManager managerCategory = new LinearLayoutManager(this);
        managerCategory.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecycle.setLayoutManager(managerCategory);
        pageModel.add(new home_page_model(1,R.drawable.ic_facebook,"#ffffff"));
        // menambahkan data untuk arraylist pada home page model untuk slider banner
        pageModel.add(new home_page_model(0,sliderModels));
        // menambahkan data untuk arraylist pada home page model untuk iklan
        // menambahkan data untuk arraylist pada home page model untuk slider banner
        pageModel.add(new home_page_model(2,"Terbaru yang ada",produkModel));
        pageModel.add(new home_page_model(3,"Lagi Trending",produkModel));
        // Menginisiasi adapter
        Home_Page_Adapter adapter = new Home_Page_Adapter(pageModel);
        // Memasukkan adapter pada recycle view
        categoryRecycle.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
