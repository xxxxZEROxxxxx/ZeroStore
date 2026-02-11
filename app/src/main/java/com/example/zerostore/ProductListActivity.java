package com.example.zerostore;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        // Back button
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // Get intent data
        int categoryId = getIntent().getIntExtra("categoryId", -1);
        boolean showOffers = getIntent().getBooleanExtra("showOffers", false);
        String categoryName = getIntent().getStringExtra("categoryName");

        // Set toolbar title
        TextView tvTitle = findViewById(R.id.tvToolbarTitle);
        if (categoryName != null) {
            tvTitle.setText(categoryName);
        }

        // Get products
        ArrayList<Product> products;
        if (showOffers) {
            products = DataProvider.getOfferProducts();
        } else if (categoryId != -1) {
            products = DataProvider.getProductsByCategory(categoryId);
        } else {
            products = DataProvider.getProducts();
        }

        // Setup RecyclerView
        RecyclerView rvProducts = findViewById(R.id.rvProductList);
        adapter = new ProductAdapter(this, products);
        rvProducts.setLayoutManager(new LinearLayoutManager(this));
        rvProducts.setAdapter(adapter);

        // Search filter
        EditText etSearch = findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
