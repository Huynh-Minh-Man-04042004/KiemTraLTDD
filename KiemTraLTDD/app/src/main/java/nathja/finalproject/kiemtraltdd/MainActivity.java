package nathja.finalproject.kiemtraltdd;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

import nathja.finalproject.kiemtraltdd.Adapter.GridAdapter;
import nathja.finalproject.kiemtraltdd.api.ApiService;
import nathja.finalproject.kiemtraltdd.api.RetrofitClient;
import nathja.finalproject.kiemtraltdd.model.Category;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private GridAdapter gridAdapter;
    private ApiService apiService;
    List<Category> lastProductList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        //anh xa
        gridView = findViewById(R.id.gridView);
//        gridAdapter = new GridAdapter(this, null);
//        gridView.setAdapter(gridAdapter);
        loadLastProduct();


    }
    private void loadLastProduct() {
        apiService.getLastProduct().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    lastProductList = response.body(); // Nhận danh sách sản phẩm

                    // Cập nhật adapter với dữ liệu mới
                    gridAdapter = new GridAdapter(MainActivity.this, lastProductList);
                    gridView.setAdapter(gridAdapter);

                    gridAdapter.notifyDataSetChanged(); // Cập nhật UI
                } else {
                    Log.e("API_ERROR", "Lỗi API: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("API_ERROR", "Lỗi kết nối: " + t.getMessage());
            }
        });
    }

}