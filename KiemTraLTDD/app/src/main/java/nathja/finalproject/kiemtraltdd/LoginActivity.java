package nathja.finalproject.kiemtraltdd;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText mUsernameView;
    private EditText mPasswordView;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameView = findViewById(R.id.editTextUsername);
        mPasswordView = findViewById(R.id.editTextPassword);
        ImageView mLoginButton = findViewById(R.id.imageViewLogin);
        prefManager = new PrefManager(this);

        // Nếu đã lưu thông tin đăng nhập, tự động chuyển đến MainActivity
        if (!prefManager.isUserLoggedOut()) {
            startMainActivity();
        }

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        TextView tvRegister = findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void attemptLogin() {
        String username = mUsernameView.getText().toString().trim();
        String password = mPasswordView.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError("Vui lòng nhập tên đăng nhập");
            return;
        }
        if (TextUtils.isEmpty(password) || password.length() < 5) {
            mPasswordView.setError("Mật khẩu phải có ít nhất 5 ký tự");
            return;
        }

        if (username.equals("admin") && password.equals("admin")) {
            Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

            // lưu thông tin đăng nhập
            prefManager.saveLoginDetails(username, password);

            startMainActivity();
        } else {
            Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu sai!", Toast.LENGTH_SHORT).show();
        }
    }


    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Kết thúc LoginActivity để không quay lại màn hình đăng nhập khi nhấn Back
    }
}
