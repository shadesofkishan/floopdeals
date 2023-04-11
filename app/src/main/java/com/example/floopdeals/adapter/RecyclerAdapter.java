package com.example.floopdeals.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.floopdeals.R;
import com.example.floopdeals.model.Item;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    List<Item> products;

    public RecyclerAdapter(List<Item> products) {
        this.products = products;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_customdesign,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

       // Product itemHolder = products.get(position);
        holder.rvName.setText(products.get(position).getName());
        holder.rvPrice.setText(products.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView rvName,rvPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            rvName = itemView.findViewById(R.id.rv_titleName);
            rvPrice = itemView.findViewById(R.id.rv_price);

        }
    }
}
