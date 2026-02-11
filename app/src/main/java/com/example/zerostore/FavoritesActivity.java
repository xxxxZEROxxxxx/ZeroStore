package com.example.zerostore;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Set;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView rvFavorites;
    private TextView tvEmpty;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        rvFavorites = findViewById(R.id.rvFavorites);
        tvEmpty = findViewById(R.id.tvEmptyFavorites);
        rvFavorites.setLayoutManager(new LinearLayoutManager(this));

        loadFavorites();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload favorites in case items were removed from detail view and returned
        // here
        loadFavorites();
    }

    private void loadFavorites() {
        Set<String> favoriteIds = FavoritesManager.getInstance(this).getFavoritesSet();
        ArrayList<Product> allProducts = DataProvider.getProducts();
        ArrayList<Product> favoriteProducts = new ArrayList<>();

        for (Product p : allProducts) {
            if (favoriteIds.contains(String.valueOf(p.getId()))) {
                favoriteProducts.add(p);
            }
        }

        if (favoriteProducts.isEmpty()) {
            rvFavorites.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.VISIBLE);
        } else {
            rvFavorites.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.GONE);
            adapter = new ProductAdapter(this, favoriteProducts);
            rvFavorites.setAdapter(adapter);
        }
    }

    public void onBackClicked(View view) {
        finish();
    }
}
