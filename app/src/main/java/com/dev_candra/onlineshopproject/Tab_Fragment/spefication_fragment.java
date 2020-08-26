package com.dev_candra.onlineshopproject.Tab_Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.adapter.produk_spefication_adapter;
import com.dev_candra.onlineshopproject.model.produk_spefication_model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class spefication_fragment extends Fragment {

    private produk_spefication_adapter adapter;
    private RecyclerView tv_proudk_recycle;
    private List<produk_spefication_model> modellist;

    public spefication_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_spefication_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_proudk_recycle = view.findViewById(R.id.tv_spefication_produk);
        modellist = new ArrayList<>();
        Aksi();
    }



    private void Aksi(){
        modellist.add(new produk_spefication_model(0,"general"));
        modellist.add(new produk_spefication_model(1,"Komputer","Ram 4 gb"));
        modellist.add(new produk_spefication_model(1,"Komputer","Ram 4 gb"));
        modellist.add(new produk_spefication_model(1,"Komputer","Ram 4 gb"));
        modellist.add(new produk_spefication_model(1,"Komputer","Ram 4 gb"));
        modellist.add(new produk_spefication_model(1,"Komputer","Ram 4 gb"));
        modellist.add(new produk_spefication_model(1,"Komputer","Ram 4 gb"));
        modellist.add(new produk_spefication_model(0,"terbaru"));
        modellist.add(new produk_spefication_model(1,"televisi","Lcd 4 inci"));
        modellist.add(new produk_spefication_model(1,"televisi","Lcd 4 inci"));
        modellist.add(new produk_spefication_model(1,"televisi","Lcd 4 inci"));
        modellist.add(new produk_spefication_model(1,"televisi","Lcd 4 inci"));
        adapter = new produk_spefication_adapter(getActivity(),modellist);
        LinearLayoutManager manager = new LinearLayoutManager(Objects.requireNonNull(getActivity()));
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        tv_proudk_recycle.setLayoutManager(manager);
        tv_proudk_recycle.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
