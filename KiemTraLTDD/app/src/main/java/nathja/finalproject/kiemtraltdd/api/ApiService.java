package nathja.finalproject.kiemtraltdd.api;

import nathja.finalproject.kiemtraltdd.LoginRequest;
import nathja.finalproject.kiemtraltdd.LoginResponse;
import nathja.finalproject.kiemtraltdd.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("/api/products")
    Call<List<Product>> getLastProduct();

    @POST("auth/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

}
