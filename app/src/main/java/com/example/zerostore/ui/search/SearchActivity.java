package com.example.zerostore.ui.search;
import com.example.zerostore.R;
import com.example.zerostore.data.model.*;
import com.example.zerostore.data.local.*;
import com.example.zerostore.ui.adapters.*;
import com.example.zerostore.ui.main.*;
import com.example.zerostore.ui.products.*;
import com.example.zerostore.ui.search.*;
import com.example.zerostore.ui.favorites.*;
import com.example.zerostore.ui.common.*;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView rvResults;
    private TextView tvNoResults;
    private EditText etSearch;
    private ProductAdapter adapter;
    private ArrayList<Product> allProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        rvResults = findViewById(R.id.rvSearchResults);
        tvNoResults = findViewById(R.id.tvNoResults);
        etSearch = findViewById(R.id.etSearch);

        rvResults.setLayoutManager(new LinearLayoutManager(this));

        // Load all products initially
        allProducts = DataProvider.getProducts();

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Focus on edit text
        etSearch.requestFocus();
    }

    private void filter(String query) {
        ArrayList<Product> filteredList = new ArrayList<>();

        if (query.isEmpty()) {
            rvResults.setVisibility(View.GONE);
            tvNoResults.setVisibility(View.GONE);
            return;
        }

        for (Product p : allProducts) {
            if (p.getNameAr().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(p);
            }
        }

        if (filteredList.isEmpty()) {
            rvResults.setVisibility(View.GONE);
            tvNoResults.setVisibility(View.VISIBLE);
        } else {
            rvResults.setVisibility(View.VISIBLE);
            tvNoResults.setVisibility(View.GONE);
            adapter = new ProductAdapter(this, filteredList);
            rvResults.setAdapter(adapter);
        }
    }

    public void onBackClicked(View view) {
        finish();
    }
}

