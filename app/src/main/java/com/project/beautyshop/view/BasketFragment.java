package com.project.beautyshop.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.beautyshop.R;
import com.project.beautyshop.adapter.BasketAdapter;
import com.project.beautyshop.database.DatabaseShop;
import com.project.beautyshop.database.PersonDao;
import com.project.beautyshop.database.ProductDao;
import com.project.beautyshop.listener.UpdateList;
import com.project.beautyshop.model.Product;

import java.text.NumberFormat;
import java.util.List;

public class BasketFragment extends Fragment implements UpdateList {
    CardView cardViewBasket;
    Button btnBasket;
    ProductDao productDao;
    List<Product> productList;
    TextView txtEmptyBasket;
    TextView txtSumQuantity;
    TextView txtTotalPrice;
    TextView txtPost;
    TextView txtTax;
    TextView txtSumPrice;
    RecyclerView recyclerViewBasket;
    int post = 20000, tax = 6000;
    public static BasketAdapter basketAdapter;
    NumberFormat numberFormat;
    int sumPrice;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basket, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupBasket();
        showBill();
        checkOrder();
        recyclerViewBasket.setAdapter(basketAdapter);
        recyclerViewBasket.setLayoutManager(new LinearLayoutManager(getActivity()));


        //Remove Item Whit TouchHelper--------
        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback
                (0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Product product = basketAdapter.getProductList().get(viewHolder.getAbsoluteAdapterPosition());
                removeItem(product);
                checkOrder();

            }
        });
        touchHelper.attachToRecyclerView(recyclerViewBasket);
        //OnClick Button---------------------
        btnBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), sumPrice+"", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void setupBasket() {

        productDao = DatabaseShop.getAppDataBase(getActivity()).productDao();
        productDao = DatabaseShop.getAppDataBase(getActivity()).productDao();
        //FindViewById--------
        txtSumQuantity = getView().findViewById(R.id.txtSumQuantity);
        txtTotalPrice = getView().findViewById(R.id.txtTotalPrice);
        txtPost = getView().findViewById(R.id.txtPost);
        txtTax = getView().findViewById(R.id.txtTax);
        txtSumPrice = getView().findViewById(R.id.txtSumPrice);
        recyclerViewBasket = getView().findViewById(R.id.recyclerViewBasket);
        cardViewBasket = getView().findViewById(R.id.cardViewBasket);
        btnBasket = getView().findViewById(R.id.btnBasket);
        txtEmptyBasket = getView().findViewById(R.id.txtEmptyBasket);
        //Lists---------------
        productList = productDao.searchByQuantity(0);
        //initialize----------
        basketAdapter = new BasketAdapter(getActivity(), productList, productDao, this);
        numberFormat = NumberFormat.getNumberInstance();
    }

    public void removeItem(Product product) {
        product.setQuantity(0);
        productDao.update(product);
        basketAdapter.updateList(productDao.searchByQuantity(0));
        showBill();
    }

    public void showBill() {
        txtSumQuantity.setText(productDao.getSumQuantity() + "");
        txtTotalPrice.setText(numberFormat.format(bill(productDao.searchByQuantity(0))) + "");
        txtPost.setText(numberFormat.format(post) + "");
        txtTax.setText(numberFormat.format(tax) + "");
        sumPrice = post + tax + bill(productDao.searchByQuantity(0));
        txtSumPrice.setText(numberFormat.format(sumPrice) + "");
    }

    public int bill(List<Product> productList) {
        int sum = 0;
        for (Product product : productList) {
            sum += (product.getQuantity() * product.getPrice());
        }
        return sum;
    }

    @Override
    public void updateList() {
        showBill();
        checkOrder();
    }

    //??-------
    public void checkOrder() {
        if (productDao.getSumQuantity() == 0) {
            cardViewBasket.setVisibility(View.INVISIBLE);
            btnBasket.setVisibility(View.INVISIBLE);
            txtEmptyBasket.setVisibility(View.VISIBLE);
        } else {
            cardViewBasket.setVisibility(View.VISIBLE);
            btnBasket.setVisibility(View.VISIBLE);
            txtEmptyBasket.setVisibility(View.INVISIBLE);
        }
    }
}
