package com.project.beautyshop.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.beautyshop.database.DatabaseShop;
import com.project.beautyshop.R;
import com.project.beautyshop.adapter.HorizontalRecyclerViewAdapter;
import com.project.beautyshop.adapter.OffAdapter;
import com.project.beautyshop.database.ProductDao;
import com.project.beautyshop.model.Product;

import java.util.List;

public class HomeFragment extends Fragment {
    ProductDao productDao;
    RecyclerView recyclerViewHorizontalPink;
    RecyclerView recyclerViewHorizontalYellow;
    RecyclerView recyclerViewOff;
    HorizontalRecyclerViewAdapter adapterPink;
    HorizontalRecyclerViewAdapter adapterYellow;
    OffAdapter offAdapter;
    List<Product> productListPink;
    List<Product> productListYellow;
    List<Product> productListOff;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productDao = DatabaseShop.getAppDataBase(getActivity()).productDao();
        setupHome();
        recyclerViewHorizontalPink.setAdapter(adapterPink);
        recyclerViewHorizontalPink.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        recyclerViewHorizontalYellow.setAdapter(adapterYellow);
        recyclerViewHorizontalYellow.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        recyclerViewOff.setAdapter(offAdapter);
        recyclerViewOff.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));


    }
    public void setupHome(){
        //findViewById--------
        recyclerViewHorizontalPink = getView().findViewById(R.id.recyclerViewHorizontalPink);
        recyclerViewHorizontalYellow = getView().findViewById(R.id.recyclerViewHorizontalYellow);
        recyclerViewOff = getView().findViewById(R.id.recyclerViewOff);
        //Lists---------------
        productListPink = productDao.searchByTitle(R.string.titlePink);
        productListYellow = productDao.searchByTitle(R.string.titleYellow);
        productListOff = productDao.searchByOff(0);
        //initialize----------
        adapterPink = new HorizontalRecyclerViewAdapter(getActivity(),productListPink);
        adapterYellow = new HorizontalRecyclerViewAdapter(getActivity(),productListYellow);
        offAdapter = new OffAdapter(getActivity(),productListOff);
    }

}
