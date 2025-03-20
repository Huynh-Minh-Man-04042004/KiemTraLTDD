package nathja.finalproject.kiemtraltdd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class IntroActivity extends AppCompatActivity {

    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        prefManager = new PrefManager(this);
        ConstraintLayout btnStart = findViewById(R.id.btnStart);

        if (prefManager.getToken() != "") {
            startActivity(new Intent(IntroActivity.this, MainActivity.class));
            finish(); // Đóng IntroActivity
        }
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (prefManager.isUserLoggedOut()) {
                    // Chưa đăng nhập -> Chuyển đến LoginActivity
                    startActivity(new Intent(IntroActivity.this, LoginActivity.class));
                } else {
                    // Đã đăng nhập -> Chuyển đến MainActivity
                    startActivity(new Intent(IntroActivity.this, MainActivity.class));
                }
                finish(); // Đóng IntroActivity
            }
        });
    }
}
