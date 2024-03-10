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

import com.project.beautyshop.R;
import com.project.beautyshop.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    List<Integer> imageList;
    RecyclerView recyclerViewCategory;
    CategoryAdapter categoryAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupCategory();
        recyclerViewCategory.setAdapter(categoryAdapter);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
    public void setupCategory(){
        imageList = new ArrayList<>();
        imageList.add(R.drawable.lafarrerr);
        imageList.add(R.drawable.mq);
        imageList.add(R.drawable.voche);
        imageList.add(R.drawable.amutiya);
        recyclerViewCategory = getView().findViewById(R.id.recyclerViewCategory);
        categoryAdapter = new CategoryAdapter(getActivity(),imageList);
    }
}
