package com.dev_candra.onlineshopproject.category_fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.adapter.Cart_Adapter;
import com.dev_candra.onlineshopproject.model.cart_model;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Cart_Fragment extends Fragment {

    // Inisiasi View
    private RecyclerView cartRecyclecView;
    private List<cart_model>modelList;
    private Cart_Adapter cart_adapter;



    public Cart_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Assignment view
        cartRecyclecView = view.findViewById(R.id.recycle_cart);
        modelList = new ArrayList<>();
        Aksi();
    }


    private void Aksi(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        modelList.add(new cart_model(0,R.drawable.ic_facebook,"Samsung galaxy S2",200000,2,20));
        modelList.add(new cart_model(0,R.drawable.ic_facebook,"Samsung galaxy S2",200000,2,10));
        modelList.add(new cart_model(0,R.drawable.ic_facebook,"Samsung galaxy S2",200000,2,50));
        modelList.add(new cart_model(0,R.drawable.ic_facebook,"Samsung galaxy S2",200000,2,40));
        modelList.add(new cart_model(1,2,30000,40000));
        cart_adapter = new Cart_Adapter(getActivity(),modelList);
        cartRecyclecView.setAdapter(cart_adapter);
        cart_adapter.notifyDataSetChanged();
        cartRecyclecView.setLayoutManager(layoutManager);
    }
}
