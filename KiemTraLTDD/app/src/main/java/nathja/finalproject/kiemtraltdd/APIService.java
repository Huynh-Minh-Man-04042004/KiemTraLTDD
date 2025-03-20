package nathja.finalproject.kiemtraltdd;

import java.util.List;
import java.util.Map;

import nathja.finalproject.kiemtraltdd.model.Product;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {

    @POST("register")
    Call<ResponseBody> registerUser(@Body User user);

    @POST("validate-otp-register")
    Call<ResponseBody> validateOtp(@Body Map<String, String> requestBody);
    @GET("/api/categories")
    Call<List<Category>> getCategoriesAll();

    @GET("/api/categories")
    Call<Category> getCategory();

    @GET("/api/products")
    Call<List<Product>> getLastProduct();

}
