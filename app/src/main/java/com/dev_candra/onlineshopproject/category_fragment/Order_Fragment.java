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
import com.dev_candra.onlineshopproject.adapter.Order_Adapter;
import com.dev_candra.onlineshopproject.model.order_model;
import com.google.api.Distribution;

import java.util.ArrayList;
import java.util.List;

public class Order_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private Order_Adapter order_adapter;
    private List<order_model> mdoelList;


    public Order_Fragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragement_order,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.layout_order);
        mdoelList = new ArrayList<>();
        Aksi();
    }

    private void Aksi(){
        mdoelList.add(new order_model(R.drawable.ic_facebook,"Samsung galaxy A++","Kamis,12 january 2020",2));
        mdoelList.add(new order_model(R.drawable.ic_facebook,"Samsung galaxy A++","Kamis,12 january 2020",3));
        mdoelList.add(new order_model(R.drawable.ic_facebook,"Samsung galaxy A++","Kamis,12 january 2020",1));
        mdoelList.add(new order_model(R.drawable.ic_facebook,"Samsung galaxy A++","Kamis,12 january 2020",4));
        mdoelList.add(new order_model(R.drawable.ic_facebook,"Samsung galaxy A++","Cancel",4));
        order_adapter = new Order_Adapter(getActivity(),mdoelList);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        order_adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(order_adapter);
    }
}
