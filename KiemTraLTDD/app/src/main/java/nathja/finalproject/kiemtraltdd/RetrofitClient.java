package nathja.finalproject.kiemtraltdd;

/*
   Họ tên: Nguyễn Tiến Dũng
   MSSV: 22110302
*/
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    public static Retrofit getRetrofit(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    // đường dẫn API
                    .baseUrl("https://mkhoavo.space/")
                    .addConverterFactory (GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
