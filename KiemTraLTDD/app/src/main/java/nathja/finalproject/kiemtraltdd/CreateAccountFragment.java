package nathja.finalproject.kiemtraltdd;
/*
   Họ tên: Nguyễn Tiến Dũng
   MSSV: 22110302
*/
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateAccountFragment extends Fragment {

    private EditText edtName, edtEmail, edtPassword, edtConfirmPassword, edtUsername;
    private ImageButton btnNext;
    private APIService apiService;

    public CreateAccountFragment() {
        // Constructor rỗng bắt buộc
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_account, container, false);

        // Ánh xạ View
        edtName = view.findViewById(R.id.textInputEditName);
        edtEmail = view.findViewById(R.id.textInputEditEmail);
        edtPassword = view.findViewById(R.id.textInputEditPassword);
        edtConfirmPassword = view.findViewById(R.id.confirmPassword);
        edtUsername = view.findViewById(R.id.textInputUsername);
        btnNext = view.findViewById(R.id.btnArrow);

        // Lấy instance của Retrofit từ RetrofitClient
        apiService = RetrofitClient.getRetrofit().create(APIService.class);

        btnNext.setOnClickListener(v -> {
            if (validateInput()) {
                registerUser();
            }
        });

        return view;
    }

    private boolean validateInput() {
        if (TextUtils.isEmpty(edtName.getText())) {
            edtName.setError("Name is required!");
            return false;
        }
        if (TextUtils.isEmpty(edtEmail.getText())) {
            edtEmail.setError("Email is required!");
            return false;
        }
        if (TextUtils.isEmpty(edtUsername.getText())) {
            edtUsername.setError("Username is required!");
            return false;
        }
        if (TextUtils.isEmpty(edtPassword.getText())) {
            edtPassword.setError("Password is required!");
            return false;
        }
        if (!edtPassword.getText().toString().equals(edtConfirmPassword.getText().toString())) {
            edtConfirmPassword.setError("Passwords do not match!");
            return false;
        }
        return true;
    }

    private void registerUser() {
        String name = edtName.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        User request = new User(name, username, password, email);
        apiService.registerUser(request).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        String message = response.body().string(); // Lấy chuỗi từ response
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

                        Bundle bundle = new Bundle();
                        bundle.putString("email", email); // Gửi email qua để xác thực OTP
                        ConfirmOTPFragment otpFragment = new ConfirmOTPFragment();
                        otpFragment.setArguments(bundle);

                        getParentFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, otpFragment)
                                .addToBackStack(null)
                                .commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "Error parsing response", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Registration failed!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
