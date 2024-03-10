package com.project.beautyshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.project.beautyshop.adapter.VerticalRecyclerViewAdapter;
import com.project.beautyshop.database.DatabaseShop;
import com.project.beautyshop.database.ProductDao;
import com.project.beautyshop.model.Product;

import java.util.List;

public class CategoryItemActivity extends AppCompatActivity {
    ProductDao productDao;
    List<Product> productList;
    ImageView imgCategoryItem;
    CollapsingToolbarLayout collapsingToolbarCategory;
    RecyclerView recyclerViewProductCategory;
    VerticalRecyclerViewAdapter categoryItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gategory_item);
        setupCategoryItem();

        int productMainImage =  getIntent().getIntExtra("productMainImage",1);
        productList = productDao.searchByMainImage(productMainImage);
        initializeCategoryItem(productList);
//        collapsingToolbarCategory.setTitle();
        imgCategoryItem.setImageResource(productList.get(0).getMainImage());

    }
    public void setupCategoryItem(){
        productDao = DatabaseShop.getAppDataBase(this).productDao();
        //FindViewById
        imgCategoryItem = findViewById(R.id.imgCategoryItem);
        collapsingToolbarCategory = findViewById(R.id.collapsingToolbarCategory);
        recyclerViewProductCategory = findViewById(R.id.recyclerViewProductCategory);
        //Lists---------------

        //initialize----------

    }
    public void initializeCategoryItem(List<Product> productList){
        categoryItemAdapter = new VerticalRecyclerViewAdapter(this,productList);
        recyclerViewProductCategory.setAdapter(categoryItemAdapter);
        recyclerViewProductCategory.setLayoutManager(new LinearLayoutManager(this));
    }
}