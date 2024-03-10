package com.project.beautyshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.project.beautyshop.ProductActivity;
import com.project.beautyshop.R;
import com.project.beautyshop.model.Product;

import java.text.NumberFormat;
import java.util.List;


public class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<VerticalRecyclerViewAdapter.VerticalRecyclerViewViewHolder> {
    Context context;
    List<Product> productList;
    NumberFormat numberFormat;

    public VerticalRecyclerViewAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        numberFormat = NumberFormat.getNumberInstance();
    }

    @NonNull
    @Override
    public VerticalRecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_vertical,parent,false);
        return new VerticalRecyclerViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalRecyclerViewViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.imgVertical.setImageResource(product.getImage());
        holder.txtNameVertical.setText(product.getName());
        holder.txtTitleVertical.setText(product.getTitle());
        holder.txtPriceVertical.setText(numberFormat.format(product.getPrice())+"");

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class VerticalRecyclerViewViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewVertical;
        ImageView imgVertical;
        TextView txtNameVertical;
        TextView txtTitleVertical;
        TextView txtPriceVertical;
        public VerticalRecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imgVertical=itemView.findViewById(R.id.imgVertical);
            txtNameVertical=itemView.findViewById(R.id.txtNameVertical);
            txtTitleVertical=itemView.findViewById(R.id.txtTitleVertical);
            txtPriceVertical=itemView.findViewById(R.id.txtPriceVertical);
            cardViewVertical=itemView.findViewById(R.id.cardViewVertical);
            cardViewVertical.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProductActivity.class);
                    intent.putExtra("productId",productList.get(getAbsoluteAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
