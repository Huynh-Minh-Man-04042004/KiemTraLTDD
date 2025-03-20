package nathja.finalproject.kiemtraltdd;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    @GET("/api/categories")
    Call<List<Category>> getCategoriesAll();

    @GET("/api/categories")
    Call<Category> getCategory();

}
