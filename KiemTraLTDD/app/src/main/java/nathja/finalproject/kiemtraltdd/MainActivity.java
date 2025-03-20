package nathja.finalproject.kiemtraltdd;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nathja.finalproject.kiemtraltdd.Adapter.ProductAdapter;
import nathja.finalproject.kiemtraltdd.api.ApiService;
import nathja.finalproject.kiemtraltdd.api.RetrofitClient;
import nathja.finalproject.kiemtraltdd.model.Product;
import retrofit2.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private ProductAdapter productAdapter;
    private ApiService apiService;
    List<Product> lastProductList;
    // Khai báo Adapter
    CategoryAdapter categoryAdapter;
    APIService apiService;
    List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        GetCategory();

        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        //anh xa
        gridView = findViewById(R.id.gridView);

        loadLastProduct();
    }

    private void AnhXa() {
        rcCate = (RecyclerView) findViewById(R.id.recyclerViewCategoryList);
    }

    private void GetCategory() {
        // Gọi Interface trong APIService
        apiService = RetrofitClient.getRetrofit().create(APIService.class);

        apiService.getCategoriesAll().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {

                if (response.isSuccessful()) {
                    categoryList = response.body(); // nhận mảng

                    // Khởi tạo Adapter
                    categoryAdapter = new CategoryAdapter(MainActivity.this, categoryList);
                    rcCate.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(MainActivity.this,
                                    LinearLayoutManager.HORIZONTAL, false);
                    rcCate.setLayoutManager(layoutManager);
                    rcCate.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();

                } else {
                    int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("Logg", t.getMessage());
            }
        });
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