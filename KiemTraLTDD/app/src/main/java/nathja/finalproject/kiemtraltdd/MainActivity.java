package nathja.finalproject.kiemtraltdd;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        //anh xa
        gridView = findViewById(R.id.gridView);

        loadLastProduct();
    }

    private void loadLastProduct() {
        apiService.getLastProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    lastProductList = response.body(); // Nhận danh sách sản phẩm

                    // Cập nhật adapter với dữ liệu mới
                    productAdapter = new ProductAdapter(MainActivity.this, lastProductList);
                    gridView.setAdapter(productAdapter);

                    productAdapter.notifyDataSetChanged(); // Cập nhật UI
                } else {
                    Log.e("API_ERROR", "Lỗi API: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("API_ERROR", "Lỗi kết nối: " + t.getMessage());
            }
        });
    }
}