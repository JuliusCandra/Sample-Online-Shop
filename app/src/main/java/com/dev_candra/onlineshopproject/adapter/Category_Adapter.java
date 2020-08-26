package com.dev_candra.onlineshopproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dev_candra.onlineshopproject.R;
import com.dev_candra.onlineshopproject.activity.Category_Activity;
import com.dev_candra.onlineshopproject.model.model_category;
import com.google.android.material.textview.MaterialTextView;

import java.net.Inet4Address;
import java.util.ArrayList;

public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.ViewHolder> {
    private ArrayList<model_category> categories;
    private Context context;

    public Category_Adapter(Context context, ArrayList<model_category> categories) {
        this.context = context;
       this.categories = categories;
    }

    @NonNull
    @Override
    public Category_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.categoty_item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Category_Adapter.ViewHolder holder, final int position) {
        final model_category model_category = categories.get(position);
        Glide.with(holder.itemView.getContext())
                .load(model_category.getCategoryLogo())
                .apply(new RequestOptions().override(30,30))
                .into(holder.imageView);
        holder.textView.setText(model_category.getCategoryName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != 0) {
                    Intent categoryNama = new Intent(context, Category_Activity.class);
                    categoryNama.putExtra("category", model_category.getCategoryName());
                    context.startActivity(categoryNama);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private MaterialTextView textView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView2);
            textView = itemView.findViewById(R.id.text_Home);
        }
    }
}
