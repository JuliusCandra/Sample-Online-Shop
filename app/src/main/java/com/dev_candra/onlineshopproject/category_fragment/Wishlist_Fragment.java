package com.dev_candra.onlineshopproject.category_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.adapter.Wishlist_Adapter;
import com.dev_candra.onlineshopproject.databinding.FragmentWishlistBinding;
import com.dev_candra.onlineshopproject.model.wishlist_model;

import java.util.ArrayList;
import java.util.List;

public class Wishlist_Fragment extends Fragment {

    private RecyclerView recyclerViewWishlist;
    private Wishlist_Adapter adapterl;
    private List<wishlist_model>models;

    public Wishlist_Fragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wishlist,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewWishlist = view.findViewById(R.id.recycel_wishlist);
        Aksi();
    }

    private void Aksi(){
        models = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        models.add(new wishlist_model(R.drawable.ic_facebook,"Samasung galaxy A++",20,"3",40,400000,"Cash on delivery method"));
        models.add(new wishlist_model(R.drawable.ic_facebook,"Samasung galaxy A++",30,"3",40,400000,"Cash on delivery method"));
        models.add(new wishlist_model(R.drawable.ic_facebook,"Samasung galaxy A++",40,"3",40,400000,"Cash on delivery method"));
        models.add(new wishlist_model(R.drawable.ic_facebook,"Samasung galaxy A++",50,"3",40,400000,"Cash on delivery method"));
        models.add(new wishlist_model(R.drawable.ic_facebook,"Samasung galaxy A++",60,"3",40,400000,"Cash on delivery method"));
        adapterl = new Wishlist_Adapter(getActivity(),models);
        recyclerViewWishlist.setAdapter(adapterl);
        recyclerViewWishlist.setLayoutManager(manager);
        adapterl.notifyDataSetChanged();
    }
}
