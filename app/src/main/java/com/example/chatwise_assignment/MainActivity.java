package com.example.chatwise_assignment;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button viewProductsButton;
    private RecyclerView recyclerView;
    private ProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewProductsButton = findViewById(R.id.viewProductsButton);
        recyclerView = findViewById(R.id.productsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewProductsButton.setOnClickListener(v -> fetchProducts());
    }

    private void fetchProducts() {
        ProductApiService service = ApiClient.getClient().create(ProductApiService.class);
        Call<List<Product>> call = service.getProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> products = response.body();
                    if (products != null && !products.isEmpty()) {
                        // Add title and products to the list for RecyclerView
                        adapter = new ProductsAdapter(MainActivity.this, products);
                        recyclerView.setAdapter(adapter);
                    }
                } else {
                    // Handle unsuccessful response
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                // Handle failure
            }
        });
    }
}
