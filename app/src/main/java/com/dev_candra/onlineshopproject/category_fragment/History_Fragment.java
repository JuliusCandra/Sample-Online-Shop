package com.dev_candra.onlineshopproject.category_fragment;

import android.content.ReceiverCallNotAllowedException;
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
import com.dev_candra.onlineshopproject.adapter.History_Adapter;
import com.dev_candra.onlineshopproject.model.history_model;

import java.util.ArrayList;
import java.util.List;

public class History_Fragment extends Fragment {

    private RecyclerView viewRecycle;
    private History_Adapter adapter;
    private List<history_model> models;

    public History_Fragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.history_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewRecycle = view.findViewById(R.id.layout_recycle_history);
        models = new ArrayList<>();
        Aksi();
    }

   private void Aksi(){
        models.add(new history_model(R.drawable.gambar_slider,"Samsung A++","20000","4","20","23 Juli 2019","suskses"));
        models.add(new history_model(R.drawable.gambar_slider,"Samsung A++","20000","4","20","23 Juli 2019","gagal"));
        models.add(new history_model(R.drawable.gambar_slider,"Samsung A++","20000","4","20","23 Juli 2019","suskses"));
        models.add(new history_model(R.drawable.gambar_slider,"Samsung A++","20000","4","20","23 Juli 2019","gagal"));
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new History_Adapter(getActivity(),models);
        viewRecycle.setAdapter(adapter);
        viewRecycle.setLayoutManager(manager);
        adapter.notifyDataSetChanged();
   }
}
