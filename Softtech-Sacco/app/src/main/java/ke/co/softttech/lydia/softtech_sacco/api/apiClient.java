package ke.co.softttech.lydia.softtech_sacco.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apiClient {
    public static Retrofit retrofit;
    private static apiClient instance = null;
    private Api myApi;

    public static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(Api.BASE_POST)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private apiClient(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_POST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(Api.class);
    }

    public static synchronized apiClient getInstance(){
        if (instance == null){
            instance = new apiClient();
        }

        return instance;
    }
    public Api getMyApi(){
        return myApi;
    }
}
