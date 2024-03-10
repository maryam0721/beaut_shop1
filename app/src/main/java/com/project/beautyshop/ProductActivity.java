package com.project.beautyshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.beautyshop.database.DatabaseShop;
import com.project.beautyshop.database.ProductDao;
import com.project.beautyshop.model.Product;
import com.project.beautyshop.view.BasketFragment;

import java.text.NumberFormat;
import java.util.List;


public class ProductActivity extends AppCompatActivity {
    ProductDao productDao;
    Product product;
    ImageView imgProductActivity;
    TextView txtProductNameActivity;
    TextView txtShortDescription;
    TextView txtLongDescription;
    TextView txtPriceProductActivity;
    TextView quantityProduct;
    ImageView imgMines;
    ImageView imgAdd;
    Button btnAdd;
    ConstraintLayout constraintLayoutCounter;
    NumberFormat numberFormat = NumberFormat.getNumberInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        setupProduct();
        int productId = getIntent().getIntExtra("productId", 1);
        product = productDao.searchById(productId);
        initializeProduct(product);

        checkQuantity();

        btnAdd.setOnClickListener(v -> addToProductQuantity(product));
        imgMines.setOnClickListener(v -> removeFromProductQuantity(product));
        imgAdd.setOnClickListener(v -> addToProductQuantity(product));

    }

    public void setupProduct() {
        productDao = DatabaseShop.getAppDataBase(this).productDao();
        //FindViewById-----------
        imgProductActivity = findViewById(R.id.imgProductActivity);
        txtProductNameActivity = findViewById(R.id.txtProductNameActivity);
        txtShortDescription = findViewById(R.id.txtShortDescription);
        txtLongDescription = findViewById(R.id.txtLongDescription);
        txtPriceProductActivity = findViewById(R.id.txtPriceProductActivity);
        quantityProduct = findViewById(R.id.quantityProduct);
        imgMines = findViewById(R.id.imgMines);
        imgAdd = findViewById(R.id.imgAdd);
        btnAdd = findViewById(R.id.btnAdd);
        constraintLayoutCounter = findViewById(R.id.constraintLayoutCounter);

    }

    public void initializeProduct(Product product) {
        imgProductActivity.setImageResource(product.getImage());
        txtProductNameActivity.setText(product.getName());
        txtShortDescription.setText(product.getShortDescription());
        txtLongDescription.setText(product.getLongDescription());
        txtPriceProductActivity.setText(numberFormat.format(product.getPrice()) + "");
        quantityProduct.setText(product.getQuantity() + "");
    }

    public void addToProductQuantity(Product product) {
        BasketFragment.basketAdapter.addToProductQuantity(product);
        quantityProduct.setText(product.getQuantity() + "");
        checkQuantity();
    }

    public void removeFromProductQuantity(Product product) {
        BasketFragment.basketAdapter.removeFromProductQuantity(product);
        quantityProduct.setText(product.getQuantity() + "");
        checkQuantity();
    }

    public void checkQuantity() {
        if (product.getQuantity() == 0) {
            btnAdd.setVisibility(View.VISIBLE);
            constraintLayoutCounter.setVisibility(View.INVISIBLE);
        } else {
            btnAdd.setVisibility(View.INVISIBLE);
            constraintLayoutCounter.setVisibility(View.VISIBLE);
        }
    }
}