package com.example.chatwise_assignment;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductApiService {
    @GET("products")
    Call<List<Product>> getProducts();
}

