package nathja.finalproject.kiemtraltdd;
/*
   Họ tên: Nguyễn Tiến Dũng
   MSSV: 22110302
*/
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface APIService {
    @POST("register")
    Call<ResponseBody> registerUser(@Body User user);

    @POST("validate-otp-register")
    Call<ResponseBody> validateOtp(@Body Map<String, String> requestBody);
}
