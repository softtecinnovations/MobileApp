package ke.co.softttech.lydia.softtech_sacco.network;

public class APIUtils {
    
    private APIUtils() {}

    public static final String BASE_URL = "https://8781fd9da7b2.ngrok.io/";

    public static RetrofitAPI getAPIService(){

        return RetrofitClientInstance.getClient(BASE_URL).create(RetrofitAPI.class);
    }

}
