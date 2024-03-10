package com.project.beautyshop.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.project.beautyshop.R;
import com.project.beautyshop.database.ProductDao;
import com.project.beautyshop.listener.UpdateList;
import com.project.beautyshop.model.Product;

import java.text.NumberFormat;
import java.util.List;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketViewHolder> {
    Context context;
    List<Product> productList;
    ProductDao productDao;
    UpdateList updateBill;
    NumberFormat numberFormat;

    public BasketAdapter(Context context, List<Product> productList, ProductDao productDao, UpdateList updateBill) {
        this.context = context;
        this.productList = productList;
        this.productDao = productDao;
        this.updateBill = updateBill;
        numberFormat = NumberFormat.getNumberInstance();
    }


    @NonNull
    @Override
    public BasketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BasketViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_basket, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BasketViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.imgBasket.setImageResource(product.getImage());
        holder.txtNameBasket.setText(product.getName());
        holder.txtTitleBasket.setText(product.getTitle());
        holder.txtPriceBasket.setText(numberFormat.format(product.getPrice()) + "");
        holder.quantityBasket.setText(product.getQuantity() + "");

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class BasketViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewBasket;
        ImageView imgBasket;
        ImageView imgAddBasket;
        ImageView imgMinesBasket;
        TextView txtNameBasket;
        TextView txtTitleBasket;
        TextView txtPriceBasket;
        TextView quantityBasket;

        public BasketViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewBasket = itemView.findViewById(R.id.cardViewBasket);
            imgBasket = itemView.findViewById(R.id.imgBasket);
            imgAddBasket = itemView.findViewById(R.id.imgAddBasket);
            imgMinesBasket = itemView.findViewById(R.id.imgMinesBasket);
            txtNameBasket = itemView.findViewById(R.id.txtNameBasket);
            txtTitleBasket = itemView.findViewById(R.id.txtTitleBasket);
            txtPriceBasket = itemView.findViewById(R.id.txtPriceBasket);
            quantityBasket = itemView.findViewById(R.id.quantityBasket);
            imgAddBasket.setOnClickListener(v -> {
                addToProductQuantity(productList.get(getAbsoluteAdapterPosition()));
            });
            imgMinesBasket.setOnClickListener(v -> {
                removeFromProductQuantity(productList.get(getAbsoluteAdapterPosition()));
            });
        }
    }

    public void addToProductQuantity(Product product) {
        product.setQuantity(product.getQuantity() + 1);
        productDao.update(product);

        updateList(productDao.searchByQuantity(0));
        updateBill.updateList();

    }

    public void removeFromProductQuantity(Product product) {
        if (product.getQuantity() > 0) {
            product.setQuantity(product.getQuantity() - 1);
            productDao.update(product);
            if ((product.getQuantity() == 0)) {
                productList.remove(product);
            }
            updateList(productDao.searchByQuantity(0));
            updateBill.updateList();
        }
    }

    public void updateList(List<Product> productList){
        this.productList = productList;
        this.notifyDataSetChanged();
    }
    public List<Product> getProductList() {
        return productList;
    }
}
