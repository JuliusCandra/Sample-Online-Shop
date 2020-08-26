package com.dev_candra.onlineshopproject.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.dev_candra.onlineshopproject.Tab_Fragment.deskripsi_fragment;
import com.dev_candra.onlineshopproject.Tab_Fragment.other_fragment;
import com.dev_candra.onlineshopproject.Tab_Fragment.spefication_fragment;

public class Produk_Detail_Adapter extends FragmentPagerAdapter {

    private int tabs;


    public Produk_Detail_Adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.tabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                deskripsi_fragment deskrpsi_produk = new deskripsi_fragment();
                return deskrpsi_produk;
            case 1:
                spefication_fragment spefikasi_produk = new spefication_fragment();
                return spefikasi_produk;
            case 2:
                other_fragment other_produk = new other_fragment();
                return other_produk;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabs;
    }
}
