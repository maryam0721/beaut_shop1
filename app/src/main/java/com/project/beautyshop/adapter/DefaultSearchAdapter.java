package com.project.beautyshop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.beautyshop.database.DatabaseShop;
import com.project.beautyshop.R;
import com.project.beautyshop.database.ProductDao;
import com.project.beautyshop.listener.UpdateListSearch;

import java.util.List;

public class DefaultSearchAdapter extends RecyclerView.Adapter<DefaultSearchAdapter.DefaultSearchViewHolder> {
    Context context;
    List<String> defaultSearch;
    UpdateListSearch updateList;
    ProductDao productDao;
    boolean select = true;
    boolean check = true;
    int row = -1;


    public DefaultSearchAdapter(Context context, List<String> defaultSearch, UpdateListSearch updateList) {
        this.context = context;
        this.defaultSearch = defaultSearch;
        this.updateList = updateList;
        productDao = DatabaseShop.getAppDataBase(context).productDao();
    }

    @NonNull
    @Override
    public DefaultSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_default_search, parent, false);
        return new DefaultSearchViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull DefaultSearchViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtDefaultSearch.setText(defaultSearch.get(position));
        if (check) {
            updateList.callBackList(defaultSearch.get(position));
            check = false;
        }
        if (select || row == position) {
            holder.txtDefaultSearch.setBackgroundResource(R.drawable.default_search_background);
            select = false;
        } else {
            holder.txtDefaultSearch.setBackgroundResource(R.drawable.search_background);
        }
    }


    @Override
    public int getItemCount() {
        return defaultSearch.size();
    }

    public class DefaultSearchViewHolder extends RecyclerView.ViewHolder {
        TextView txtDefaultSearch;

        public DefaultSearchViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDefaultSearch = itemView.findViewById(R.id.txtDefaultSearch);

            txtDefaultSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    row = getAbsoluteAdapterPosition();
                    updateList.callBackList(defaultSearch.get(getAbsoluteAdapterPosition()));
                    notifyDataSetChanged();
                }
            });
        }
    }
}
