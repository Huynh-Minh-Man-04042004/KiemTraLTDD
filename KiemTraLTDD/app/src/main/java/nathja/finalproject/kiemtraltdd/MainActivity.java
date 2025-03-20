package nathja.finalproject.kiemtraltdd;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Callback;


import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcCate;

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
    }

    private void AnhXa() {
        rcCate = (RecyclerView) findViewById(R.id.recyclerViewCategoryList);
    }

    private void GetCategory() {
        // Gọi Interface trong APIService
        apiService = RetrofitClient.getRetrofit().create(APIService.class);
        Log.d("API_CALL", "Calling API...");
        Toast.makeText(MainActivity.this, "calling api", Toast.LENGTH_LONG).show();

        apiService.getCategoriesAll().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                Log.d("API_CALL", "Response received!");
                Toast.makeText(MainActivity.this, "in function", Toast.LENGTH_LONG).show();

                if (response.isSuccessful()) {
                    categoryList = response.body(); // nhận mảng
                    Toast.makeText(MainActivity.this, "Total categories: " + categoryList.size(), Toast.LENGTH_LONG).show();

                    // Khởi tạo Adapter
                    categoryAdapter = new CategoryAdapter(MainActivity.this, categoryList);
                    rcCate.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(MainActivity.this,
                                    LinearLayoutManager.HORIZONTAL, false);
                    rcCate.setLayoutManager(layoutManager);
                    rcCate.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();
                    Log.d("API_RESPONSE", "Data: " + categoryList);

                } else {
                    int statusCode = response.code();
                    Log.e("API_RESPONSE", "Failed: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("Logg", t.getMessage());
            }
        });
    }

}