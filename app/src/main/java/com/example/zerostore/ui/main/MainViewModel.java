package com.example.zerostore.ui.main;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.zerostore.data.model.Category;
import com.example.zerostore.data.model.Product;
import com.example.zerostore.data.repository.AppRepository;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private AppRepository repository;

    public MainViewModel(Application application) {
        super(application);
        repository = new AppRepository(application);
    }

    public LiveData<List<Category>> getCategories() {
        return repository.getAllCategories();
    }

    public LiveData<List<Product>> getTopProducts() {
        return repository.getTopProducts();
    }
}
