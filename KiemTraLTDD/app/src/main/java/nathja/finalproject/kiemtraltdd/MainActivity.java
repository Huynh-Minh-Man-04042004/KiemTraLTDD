package nathja.finalproject.kiemtraltdd;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import nathja.finalproject.kiemtraltdd.Adapter.ProductAdapter;
import nathja.finalproject.kiemtraltdd.api.ApiService;
import nathja.finalproject.kiemtraltdd.api.RetrofitClient;
import nathja.finalproject.kiemtraltdd.model.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private ProductAdapter productAdapter;
    private ApiService apiService;
    List<Product> lastProductList;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        //anh xa
        gridView = findViewById(R.id.gridView);

        loadLastProduct();
        prefManager = new PrefManager(this);
        Button btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefManager.logout();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private void loadLastProduct() {
        apiService.getLastProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    lastProductList = response.body(); // Nh?n danh sách s?n ph?m

                    // C?p nh?t adapter v?i d? li?u m?i
                    productAdapter = new ProductAdapter(MainActivity.this, lastProductList);
                    gridView.setAdapter(productAdapter);

                    productAdapter.notifyDataSetChanged(); // C?p nh?t UI
                } else {
                    Log.e("API_ERROR", "L?i API: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("API_ERROR", "L?i k?t n?i: " + t.getMessage());
            }
        });
    }
}