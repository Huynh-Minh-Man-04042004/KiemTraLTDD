package nathja.finalproject.kiemtraltdd;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nathja.finalproject.kiemtraltdd.Adapter.ProductAdapter;
import nathja.finalproject.kiemtraltdd.api.RetrofitClient;
import nathja.finalproject.kiemtraltdd.model.Product;
import retrofit2.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private RecyclerView rcCate;
    private ProductAdapter productAdapter;
    private APIService apiService;
    List<Product> lastProductList;
    private PrefManager prefManager;
    private TextView name;
    private String token;

    // Khai báo Adapter
    CategoryAdapter categoryAdapter;
    List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        GetCategory();

        apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);

        name = findViewById(R.id.textView4);
        prefManager = new PrefManager(this);
        //anh xa
        gridView = findViewById(R.id.gridView);
        token = getIntent().getStringExtra("TOKEN") != null
                ? getIntent().getStringExtra("TOKEN")
                : prefManager.getToken();
        fetchProfile(token);
        if (token != null) {
            Log.d("MainActivity", "Token nhận được: " + token);
        } else {
            Log.e("MainActivity", "Không nhận được token!");
        }



        loadLastProduct();
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

    private void AnhXa() {
        rcCate = (RecyclerView) findViewById(R.id.recyclerViewCategoryList);
    }
    private void fetchProfile(String token) {
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<ProfileResponse> call = apiService.getProfile("Bearer " + token);

        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String ten = response.body().getName();
                    name.setText(ten); // Hiển thị tên lên TextView
                } else {
                    Toast.makeText(MainActivity.this, "Không thể lấy thông tin người dùng!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Log.e("MainActivity", "Lỗi kết nối: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Lỗi kết nối!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GetCategory() {
        // Gọi Interface trong APIService
        apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
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

                    lastProductList = response.body(); //



                    productAdapter = new ProductAdapter(MainActivity.this, lastProductList);
                    gridView.setAdapter(productAdapter);


                    productAdapter.notifyDataSetChanged(); //
                } else {

                    Log.e("API_ERROR", "error " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("API_ERROR", ": " + t.getMessage());
            }
        });
    }
}