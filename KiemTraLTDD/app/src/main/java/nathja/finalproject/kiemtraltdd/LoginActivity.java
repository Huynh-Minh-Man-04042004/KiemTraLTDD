package nathja.finalproject.kiemtraltdd;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import nathja.finalproject.kiemtraltdd.api.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private ImageView ivBtnLogin;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.editTextUsername);
        etPassword = findViewById(R.id.editTextPassword);
        ivBtnLogin = findViewById(R.id.imageView2);

        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        ivBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        LoginRequest request = new LoginRequest(email, password);
        Call<LoginResponse> call = apiService.loginUser(request);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    runOnUiThread(() -> {
                        Toast.makeText(LoginActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();

                        // Chuyển sang MainActivity sau khi hiển thị Toast xong
                        new Handler(Looper.getMainLooper()).postDelayed(() -> {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }, 500); // Delay 1 giây để Toast hiển thị rõ
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "API Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
