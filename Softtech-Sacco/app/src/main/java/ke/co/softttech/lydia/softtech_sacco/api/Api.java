package ke.co.softttech.lydia.softtech_sacco.api;


import java.util.List;

import ke.co.softttech.lydia.softtech_sacco.models.PersonModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @GET("sacco")
    Call<List<model>> getsuperHeroes(
            @Query("customer_name") String login
    );


    @GET("member")
    Call<List<members>> getSaccos();

    String BASE_POST = "https://c3b8c02e17dd.ngrok.io/";
    @POST("save")
    Call<List<members>> createPost(@Body members personModel);


    @GET("all")
    Call<List<members>> getSaccoss();
}
