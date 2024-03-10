package com.project.beautyshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.project.beautyshop.ProductActivity;
import com.project.beautyshop.R;
import com.project.beautyshop.model.Product;

import java.text.NumberFormat;
import java.util.List;

public class OffAdapter extends RecyclerView.Adapter<OffAdapter.OffViewHolder> {
    Context context;
    List<Product> productList;
    NumberFormat numberFormat;

    public OffAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        numberFormat = NumberFormat.getNumberInstance();
    }

    @NonNull
    @Override
    public OffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OffViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_off,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OffViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.imgOff.setImageResource(product.getImage());
        holder.txtNameOff.setText(product.getName());
        holder.txtPriceOff.setText(numberFormat.format(product.getPrice())+"");
        holder.txtRawPrice.setText(numberFormat.format(product.getRawPrice())+"");
        holder.txtRawPrice.setPaintFlags(holder.txtRawPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class OffViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewOff;
        ImageView imgOff;
        TextView txtNameOff;
        TextView txtPriceOff;
        TextView txtRawPrice;
        public OffViewHolder(@NonNull View itemView) {
            super(itemView);
            imgOff=itemView.findViewById(R.id.imgOff);
            txtNameOff=itemView.findViewById(R.id.txtNameOff);
            txtPriceOff=itemView.findViewById(R.id.txtPriceOff);
            txtRawPrice=itemView.findViewById(R.id.txtRawPrice);
            cardViewOff=itemView.findViewById(R.id.cardViewOff);
            cardViewOff.setOnClickListener(new View.OnClickListener() {
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
