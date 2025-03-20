package nathja.finalproject.kiemtraltdd;

import static nathja.finalproject.kiemtraltdd.BaseClient.createService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://mkhoavo.space/";
    private static Retrofit retrofit;


    public static Retrofit getRetrofit() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}