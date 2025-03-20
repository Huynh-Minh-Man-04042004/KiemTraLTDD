package nathja.finalproject.kiemtraltdd;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    Context context;

    // Constructor
    public PrefManager(Context context) {
        this.context = context;
    }

    // Lưu thông tin đăng nhập
    public void saveLoginDetails(String username, String password, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Username", username);
        editor.putString("Password", password);
        editor.putString("Token", token);
        editor.apply();
    }

    public String getUsername() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Username", "");
    }

    public String getPassword() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Password", "");
    }

    public String getToken() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Token", "");
    }

    // Kiểm tra xem người dùng đã đăng xuất chưa
    public boolean isUserLoggedOut() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        boolean isEmailEmpty = sharedPreferences.getString("Username", "").isEmpty();
        boolean isPasswordEmpty = sharedPreferences.getString("Password", "").isEmpty();
        return isEmailEmpty || isPasswordEmpty;
    }

    public void logout() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear(); // Xóa toàn bộ dữ liệu đăng nhập
        editor.apply();
    }
}
