package com.project.beautyshop.view;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.beautyshop.database.DatabaseShop;
import com.project.beautyshop.R;
import com.project.beautyshop.adapter.DefaultSearchAdapter;
import com.project.beautyshop.adapter.VerticalRecyclerViewAdapter;
import com.project.beautyshop.database.ProductDao;
import com.project.beautyshop.listener.UpdateListSearch;
import com.project.beautyshop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements UpdateListSearch {
    List<String> defaultSearch;
    DefaultSearchAdapter defaultSearchAdapter;
    RecyclerView recyclerViewDefaultSearch;
    ProductDao productDao;
    RecyclerView recyclerViewSearch;
    List<Product> productList;
    VerticalRecyclerViewAdapter adapterSearch;
    EditText editTextSearch;
    TextView txtNull;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productDao = DatabaseShop.getAppDataBase(getActivity()).productDao();
        setupSearch();

        recyclerViewDefaultSearch.setAdapter(defaultSearchAdapter);
        recyclerViewDefaultSearch.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getActivity()));

        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchByName();
                    return true;
                }
                return false;
            }
        });
    }

    public void setupSearch() {
        //FindViewById-------
        recyclerViewSearch = getView().findViewById(R.id.recyclerViewSearch);
        recyclerViewDefaultSearch = getView().findViewById(R.id.recyclerViewDefaultSearch);
        editTextSearch = getView().findViewById(R.id.editTextSearch);
        txtNull = getView().findViewById(R.id.txtNull);

        //Lists---------------
        defaultSearch = new ArrayList<>();
        defaultSearch.add("همه");
        defaultSearch.add("ضد آفتاب");
        defaultSearch.add("آبرسان");
        defaultSearch.add("مرطوب کننده");
        defaultSearch.add("ترمیم کننده");
        defaultSearch.add("ضد لک");
        defaultSearch.add("ضد جوش");
        defaultSearch.add("تونر");
        defaultSearch.add("میسلار واتر");
        defaultSearch.add("اسپری آب");
        //initialize----------
        defaultSearchAdapter = new DefaultSearchAdapter(getActivity(), defaultSearch, this);
    }

    @Override
    public void callBackList(String defaultSearch) {
        txtNull.setVisibility(View.INVISIBLE);
        editTextSearch.setText("");
        //Lists---------------
        if (defaultSearch.equals("همه")) {
            productList = productDao.getAll();
        } else {
            productList = productDao.searchByName(defaultSearch);
        }
        if (productList.isEmpty()) {
            txtNull.setVisibility(View.VISIBLE);
        }
        //initialize----------
        initializeAdapter(productList);
    }

    public void searchByName() {
        txtNull.setVisibility(View.INVISIBLE);
        String searchKeyWord = editTextSearch.getText().toString();
        if (!searchKeyWord.isEmpty()) {
            productList = productDao.searchByName(searchKeyWord);
            if (productList.isEmpty()) {
                txtNull.setVisibility(View.VISIBLE);
            }
            InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            initializeAdapter(productList);
        } else {
            Toast.makeText(getActivity(), "محصول خود را  وارد کنید", Toast.LENGTH_SHORT).show();
        }
    }

    public void initializeAdapter(List<Product> productList) {
        adapterSearch = new VerticalRecyclerViewAdapter(getActivity(), productList);
        adapterSearch.notifyDataSetChanged();
        recyclerViewSearch.setAdapter(adapterSearch);
    }

}
