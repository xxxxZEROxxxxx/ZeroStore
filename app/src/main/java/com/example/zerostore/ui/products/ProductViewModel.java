package com.example.zerostore.ui.products;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.zerostore.data.model.Product;
import com.example.zerostore.data.repository.AppRepository;
import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private AppRepository repository;

    public ProductViewModel(Application application) {
        super(application);
        repository = new AppRepository(application);
    }

    public LiveData<List<Product>> getAllProducts() {
        return repository.getAllProducts();
    }

    public LiveData<List<Product>> getProductsByCategory(int categoryId) {
        return repository.getProductsByCategory(categoryId);
    }

    public LiveData<List<Product>> getOfferProducts() {
        return repository.getOfferProducts();
    }

    public LiveData<List<Product>> getFavoriteProducts() {
        return repository.getFavoriteProducts();
    }

    public LiveData<Product> getProductById(int productId) {
        return repository.getProductById(productId);
    }

    public LiveData<Boolean> isFavorite(int productId) {
        return repository.isFavorite(productId);
    }

    public void addFavorite(int productId) {
        repository.addFavorite(productId);
    }

    public void removeFavorite(int productId) {
        repository.removeFavorite(productId);
    }
}
