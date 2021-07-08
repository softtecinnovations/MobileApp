package ke.co.softttech.lydia.softtech_sacco.api;

import com.google.gson.annotations.SerializedName;

public class model  {
    @SerializedName("name")
    private String name;

    public model(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}

