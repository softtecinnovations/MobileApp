package ke.co.softttech.lydia.softtech_sacco.api;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL1 = "https://8781fd9da7b2.ngrok.io/";
    @GET("sacco")
    Call<List<model>> getsuperHeroes(
            @Query("customer_name") String login
    );

    String BASE_URL3 = "https://jsonplaceholder.typicode.com/";
    @GET("posts")
    Call<List<model>> getSaccos();

    String BASE_URL = "https://simplifiedcoding.net/demos/";
    @GET("marvel")
    Call<List<model>> getSaccoss();
}
