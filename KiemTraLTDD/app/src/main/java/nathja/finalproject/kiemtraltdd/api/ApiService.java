package nathja.finalproject.kiemtraltdd.api;



import java.util.List;

import nathja.finalproject.kiemtraltdd.model.Category;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("categories.php")
    Call<List<Category>> getLastProduct();
}
