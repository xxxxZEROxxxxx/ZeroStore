package com.example.zerostore.ui.products;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.zerostore.R;
import com.example.zerostore.ui.adapters.ProductAdapter;
import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    private ProductAdapter adapter;
    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

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

        // Setup RecyclerView
        RecyclerView rvProducts = findViewById(R.id.rvProductList);
        adapter = new ProductAdapter(this, new ArrayList<>());
        rvProducts.setLayoutManager(new LinearLayoutManager(this));
        rvProducts.setAdapter(adapter);

        // Observe products from ViewModel
        if (showOffers) {
            productViewModel.getOfferProducts().observe(this, products -> {
                if (products != null) adapter.updateList(products);
            });
        } else if (categoryId != -1) {
            productViewModel.getProductsByCategory(categoryId).observe(this, products -> {
                if (products != null) adapter.updateList(products);
            });
        } else {
            productViewModel.getAllProducts().observe(this, products -> {
                if (products != null) adapter.updateList(products);
            });
        }

        // Search filter
        EditText etSearch = findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}
