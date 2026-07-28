package com.example.zerostore.data.repository;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.zerostore.data.local.DatabaseHelper;
import com.example.zerostore.data.model.Category;
import com.example.zerostore.data.model.Product;
import com.example.zerostore.data.network.ApiClient;
import com.example.zerostore.data.network.ApiService;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppRepository {
    private DatabaseHelper dbHelper;
    private ApiService apiService;
    private ExecutorService executorService;

    // MutableLiveData to replace Room's auto-updating LiveData
    private MutableLiveData<List<Product>> allProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Category>> allCategoriesLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> topProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> offerProductsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> favoriteProductsLiveData = new MutableLiveData<>();

    public AppRepository(Application application) {
        dbHelper = DatabaseHelper.getInstance(application);
        apiService = ApiClient.getClient().create(ApiService.class);
        executorService = Executors.newFixedThreadPool(2);

        // Load cached data from SQLite first
        loadAllFromDb();

        // Fetch latest data from server
        refreshProducts();
        refreshCategories();
    }

    // Load all data from local SQLite into LiveData
    private void loadAllFromDb() {
        executorService.execute(() -> {
            allProductsLiveData.postValue(dbHelper.getAllProducts());
            allCategoriesLiveData.postValue(dbHelper.getAllCategories());
            topProductsLiveData.postValue(dbHelper.getTopProducts());
            offerProductsLiveData.postValue(dbHelper.getOfferProducts());
            favoriteProductsLiveData.postValue(dbHelper.getFavoriteProducts());
        });
    }

    // Refresh all LiveData after DB changes
    private void refreshLiveData() {
        allProductsLiveData.postValue(dbHelper.getAllProducts());
        topProductsLiveData.postValue(dbHelper.getTopProducts());
        offerProductsLiveData.postValue(dbHelper.getOfferProducts());
        favoriteProductsLiveData.postValue(dbHelper.getFavoriteProducts());
    }

    public LiveData<List<Product>> getAllProducts() {
        return allProductsLiveData;
    }

    public LiveData<List<Category>> getAllCategories() {
        return allCategoriesLiveData;
    }

    public void refreshProducts() {
        apiService.getProducts("*").enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    executorService.execute(() -> {
                        dbHelper.insertProducts(response.body());
                        refreshLiveData();
                        Log.d("AppRepository", "Products inserted successfully: " + response.body().size());
                    });
                } else {
                    Log.e("AppRepository", "Failed to fetch products: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("AppRepository", "Error fetching products: " + t.toString(), t);
            }
        });
    }

    public void refreshCategories() {
        apiService.getCategories("*").enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    executorService.execute(() -> {
                        dbHelper.insertCategories(response.body());
                        allCategoriesLiveData.postValue(dbHelper.getAllCategories());
                        Log.d("AppRepository", "Categories inserted successfully: " + response.body().size());
                    });
                } else {
                    Log.e("AppRepository", "Failed to fetch categories: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("AppRepository", "Error fetching categories: " + t.toString(), t);
            }
        });
    }

    public LiveData<List<Product>> getProductsByCategory(int categoryId) {
        MutableLiveData<List<Product>> liveData = new MutableLiveData<>();
        executorService.execute(() -> {
            liveData.postValue(dbHelper.getProductsByCategory(categoryId));
        });
        return liveData;
    }

    public LiveData<Product> getProductById(int productId) {
        MutableLiveData<Product> liveData = new MutableLiveData<>();
        executorService.execute(() -> {
            liveData.postValue(dbHelper.getProductById(productId));
        });
        return liveData;
    }

    public LiveData<List<Product>> getTopProducts() {
        return topProductsLiveData;
    }

    public LiveData<List<Product>> getOfferProducts() {
        return offerProductsLiveData;
    }

    public LiveData<List<Product>> getFavoriteProducts() {
        return favoriteProductsLiveData;
    }

    // Favorite Operations
    public LiveData<Boolean> isFavorite(int productId) {
        MutableLiveData<Boolean> liveData = new MutableLiveData<>();
        executorService.execute(() -> {
            liveData.postValue(dbHelper.isFavorite(productId));
        });
        return liveData;
    }

    public void addFavorite(int productId) {
        executorService.execute(() -> {
            dbHelper.insertFavorite(productId);
            favoriteProductsLiveData.postValue(dbHelper.getFavoriteProducts());
        });
    }

    public void removeFavorite(int productId) {
        executorService.execute(() -> {
            dbHelper.deleteFavorite(productId);
            favoriteProductsLiveData.postValue(dbHelper.getFavoriteProducts());
        });
    }
}
