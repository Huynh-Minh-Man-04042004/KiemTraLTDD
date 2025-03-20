package nathja.finalproject.kiemtraltdd.api;

import nathja.finalproject.kiemtraltdd.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/api/products")
    Call<List<Product>> getLastProduct();
}
