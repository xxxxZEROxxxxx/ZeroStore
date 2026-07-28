package com.example.zerostore.data.network;

import com.example.zerostore.data.model.Category;
import com.example.zerostore.data.model.Product;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("rest/v1/products")
    Call<List<Product>> getProducts(@Query("select") String select);

    @GET("rest/v1/categories")
    Call<List<Category>> getCategories(@Query("select") String select);
}
