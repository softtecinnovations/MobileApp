package ke.co.softttech.lydia.softtech_sacco.network;

import ke.co.softttech.lydia.softtech_sacco.models.PersonModel;
import ke.co.softttech.lydia.softtech_sacco.models.ServiceModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitAPI {
//    @POST("/phonenumber")
//    Call<PersonModel> createPost(@Body PersonModel personModel);
    @POST("/people")
    @FormUrlEncoded
    Call<PersonModel> createPost(@Body PersonModel personModel);
    @GET("/actions")
    Call<ServiceModel> getItems(@Body ServiceModel serviceModel);
}
