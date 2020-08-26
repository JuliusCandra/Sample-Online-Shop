package com.dev_candra.onlineshopproject.category_fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;


import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.adapter.Category_Adapter;
import com.dev_candra.onlineshopproject.adapter.Home_Page_Adapter;
import com.dev_candra.onlineshopproject.adapter.Produk_Adapter;
import com.dev_candra.onlineshopproject.method.gmail;
import com.dev_candra.onlineshopproject.method.proses_dialog;
import com.dev_candra.onlineshopproject.model.home_page_model;
import com.dev_candra.onlineshopproject.model.produk_model;
import com.dev_candra.onlineshopproject.model_data.categoy_data;
import com.dev_candra.onlineshopproject.model.model_category;
import com.dev_candra.onlineshopproject.model.slider_model;
import com.dev_candra.onlineshopproject.model_data.produk_data;
import com.dev_candra.onlineshopproject.model_data.slider_data;
import com.google.android.material.textview.MaterialTextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Fragment extends Fragment {

    // Inisiasi semua yang diperlukan
    private ArrayList<model_category> model_categories;
    private categoy_data data = new categoy_data();
    private List<slider_model> models;
    private ArrayList<produk_model> model_produk;
    private slider_data data1;
    private produk_data data_produk;
    private RecyclerView categoryRecycle;

    // Required empty public construc
    public Home_Fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Menginisasi layout untuk fragment
        return inflater.inflate(R.layout.fragment_home_, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // menginisasi arraylist pada model produk
        model_produk = new ArrayList<>();
        // menginisasi kelas sendGmail
        // menginisasi arraylist pada produk
        model_categories = new ArrayList<>();
        // menginisasi data pada produk
        data_produk = new produk_data();
        // menginisiasi arraylist pada produk
        models = new ArrayList<>();
        // menginisiasi data slider
        data1 = new slider_data();
        // menambahkan semua data produk pada arraylist model produk
        model_produk.addAll(data_produk.getDataProdukModel());
        // menambahkan semua data slider pada arraylist slider model
        models.addAll(data1.getData());
        // manambahkan semua data category data di arraylist model category
        model_categories.addAll(data.getlist());
        categoryRecycle = view.findViewById(R.id.recyle_category);
        // Method yang akan menampung semua aksi yang akan digunakan
        Aksi();
        // mengembalikan nilai pada layout yang udah di inisasi
    }

    private void Aksi() {
        getLayoutManager();
        setMultipleRecycleView();

    }

    // Method atau function yang dibuat untuk membuat recycle view
    private void getLayoutManager() {
        // Membuar recycle view selalu menetap jika terjadi perubahan pada data
        categoryRecycle.setHasFixedSize(true);
        // membuat layout manager pada recylce view
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        // menentukan orietasi yang akan dipakai layout managernya
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        // setelah menentukan layout managernya lalu masukkan kerecycle view
        categoryRecycle.setLayoutManager(manager);
        // lalu inisiasi adapter yang telah dibuat
        Category_Adapter category_adapter = new Category_Adapter(getActivity(), model_categories);
        // setelah itu masukkan adapter ke recycle view
        categoryRecycle.setAdapter(category_adapter);
    }


    // Method atau Function yang digunakan untuk membuat multiple recyle view
    private void setMultipleRecycleView(){
        // Membuat recyle view
        RecyclerView recyclerView = Objects.requireNonNull(getActivity()).findViewById(R.id.mutliple_recycle_view);
        // Menginisiasi layout manager
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        // Membuat orientasi pada layout manager
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        // meletakkan layout manager pada recyele view
//        recyclerView.setLayoutManager(manager);
        recyclerView.setLayoutManager(manager);
        // menginisiasi array list pada home page model
        ArrayList<home_page_model> pageModel = new ArrayList<>();
        // menambahkan data untuk arraylist pada home page model untuk iklan
        pageModel.add(new home_page_model(1,R.drawable.ic_facebook,"#ffffff"));
        // menambahkan data untuk arraylist pada home page model untuk slider banner
        pageModel.add(new home_page_model(0,models));
        // menambahkan data untuk arraylist pada home page model untuk iklan
        // menambahkan data untuk arraylist pada home page model untuk slider banner
        pageModel.add(new home_page_model(2,"Terbaru yang ada",model_produk));
        pageModel.add(new home_page_model(3,"Lagi Trending",model_produk));
        // Menginisiasi adapter
        Home_Page_Adapter adapter = new Home_Page_Adapter(pageModel);
        // Memasukkan adapter pada recycle view
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}
