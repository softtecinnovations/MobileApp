package ke.co.softttech.lydia.softtech_sacco.network;

public class APIUtils {
    
    private APIUtils() {}

    public static final String BASE_URL = "https://f4ef4f5225c8.ngrok.io/";

    public static RetrofitAPI getAPIService(){

        return RetrofitClientInstance.getClient(BASE_URL).create(RetrofitAPI.class);
    }

}
