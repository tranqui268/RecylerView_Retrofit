package com.example.retrofit_recylerview.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit_recylerview.DescriptionProductActivity;
import com.example.retrofit_recylerview.R;
import com.example.retrofit_recylerview.object.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context context ;
    Activity activity;
    ArrayList<Product> productList;

    public ProductAdapter(Context context, Activity activity,ArrayList<Product>   productList) {
        this.context = context;
        this.activity = activity;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int index = position;
        holder.txtTitle.setText(productList.get(position).getTitle());
        holder.txtPrice.setText(String.valueOf(productList.get(position).getPrice())+"$");
        String imgUrl = productList.get(position).getImage();
        if (imgUrl != null){
            Glide.with(context)
                    .load(imgUrl)
                    .into(holder.imageView);
        }
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String img = productList.get(index).getImage();
                String rating = String.valueOf(productList.get(index).getRating().getRate());
                String count = String.valueOf(productList.get(index).getRating().getCount());
                Intent intent = new Intent(context, DescriptionProductActivity.class);
                Bundle bundle = new Bundle();
                if (img != null){
                    bundle.putString("Img",img);
                }
                bundle.putString("Descript",productList.get(index).getDescription());
                bundle.putString("Rating",rating);
                bundle.putString("Total", count);
                intent.putExtra("Data",bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        ImageView imageView;
        TextView txtTitle;
        TextView txtPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageProduct);
            txtTitle = itemView.findViewById(R.id.nameProduct);
            txtPrice = itemView.findViewById(R.id.priceProduct);
        }
    }
}
