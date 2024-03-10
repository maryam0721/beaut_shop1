package com.project.beautyshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.project.beautyshop.ProductActivity;
import com.project.beautyshop.R;
import com.project.beautyshop.model.Product;

import java.text.NumberFormat;
import java.util.List;

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Product> productList;
    NumberFormat numberFormat;

    public HorizontalRecyclerViewAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        numberFormat = NumberFormat.getNumberInstance();
    }

    @Override
    public int getItemViewType(int position) {
//        Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 0) {
            return new ViewHolder0(LayoutInflater.from(context).inflate(R.layout.list_item_first, parent, false));
        } else {
            return new ViewHolder2(LayoutInflater.from(context).inflate(R.layout.list_item_horizontal, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        Product product = productList.get(position);
        if (holder.getItemViewType() == 0) {
            ViewHolder0 viewHolder0 = (ViewHolder0) holder;
            viewHolder0.imgFirst.setImageResource(product.getMainImage());
        } else {
            ViewHolder2 viewHolder2 = (ViewHolder2) holder;
            viewHolder2.imgHorizontal.setImageResource(product.getImage());
            viewHolder2.txtNameHorizontal.setText(product.getName());
            viewHolder2.txtPriceHorizontal.setText(numberFormat.format(product.getPrice()) + "");
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolder0 extends RecyclerView.ViewHolder {
        ImageView imgFirst;

        public ViewHolder0(View itemView) {
            super(itemView);
            imgFirst = itemView.findViewById(R.id.imgFirst);

        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        CardView cardViewHorizontal;
        ImageView imgHorizontal;
        TextView txtNameHorizontal;
        TextView txtPriceHorizontal;

        public ViewHolder2(View itemView) {
            super(itemView);
            imgHorizontal = itemView.findViewById(R.id.imgHorizontal);
            txtNameHorizontal = itemView.findViewById(R.id.txtNameHorizontal);
            txtPriceHorizontal = itemView.findViewById(R.id.txtPriceHorizontal);
            cardViewHorizontal = itemView.findViewById(R.id.cardViewHorizontal);
            cardViewHorizontal.setOnClickListener(new View.OnClickListener() {
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

