package com.example.zerostore.ui.favorites;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.zerostore.R;
import com.example.zerostore.ui.adapters.ProductAdapter;
import com.example.zerostore.ui.products.ProductViewModel;
import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView rvFavorites;
    private TextView tvEmpty;
    private ProductAdapter adapter;
    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        rvFavorites = findViewById(R.id.rvFavorites);
        tvEmpty = findViewById(R.id.tvEmptyFavorites);
        
        adapter = new ProductAdapter(this, new ArrayList<>());
        rvFavorites.setLayoutManager(new LinearLayoutManager(this));
        rvFavorites.setAdapter(adapter);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        productViewModel.getFavoriteProducts().observe(this, products -> {
            if (products == null || products.isEmpty()) {
                rvFavorites.setVisibility(View.GONE);
                tvEmpty.setVisibility(View.VISIBLE);
            } else {
                rvFavorites.setVisibility(View.VISIBLE);
                tvEmpty.setVisibility(View.GONE);
                adapter.updateList(products);
            }
        });
    }

    public void onBackClicked(View view) {
        finish();
    }
}
