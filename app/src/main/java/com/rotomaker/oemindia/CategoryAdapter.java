package com.rotomaker.oemindia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private CategoryList[] categoryList;
    private Context mCtx;
    public CategoryAdapter(CategoryList[] categoryList) {
        this.categoryList=categoryList;
    }
    FragmentManager fragmentManager;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View categoryitem= layoutInflater.inflate(R.layout.category_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(categoryitem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final CategoryList myListData = categoryList[position];
        holder.title.setText(categoryList[position].getTitle());

        holder.imageView.setImageResource(categoryList[position].getImgId());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Hi Checking", Toast.LENGTH_SHORT).show();

                fragmentManager= ((AppCompatActivity) v.getContext()).getSupportFragmentManager();
                ShopFragment shopFragment= new ShopFragment();
                fragmentManager.beginTransaction().replace(R.id.frame_Container,shopFragment).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView title,details;
        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.category_image);
            this.title = (TextView) itemView.findViewById(R.id.category_title);

            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }

    }
}
