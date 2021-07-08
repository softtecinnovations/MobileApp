package ke.co.softttech.lydia.softtech_sacco.models;

import java.io.Serializable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserModel implements Serializable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    String user_Name, user_Id, user_PhoneNumber, user_KraPin,user_SaccoName;

    public UserModel() {
    }

    public UserModel(String user_Name, String user_IdNumber, String user_PhoneNumber, String user_KraPin, String user_SaccoName) {
        this.user_Name = user_Name;
        this.user_Id = user_IdNumber;
        this.user_PhoneNumber = user_PhoneNumber;
        this.user_KraPin = user_KraPin;
        this.user_SaccoName = user_SaccoName;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public String getUser_PhoneNumber() {
        return user_PhoneNumber;
    }

    public void setUser_PhoneNumber(String user_PhoneNumber) {
        this.user_PhoneNumber = user_PhoneNumber;
    }

    public String getUser_KraPin() {
        return user_KraPin;
    }

    public void setUser_KraPin(String user_KraPin) {
        this.user_KraPin = user_KraPin;
    }

    public String getUser_SaccoName() {
        return user_SaccoName;
    }

    public void setUser_SaccoName(String user_SaccoName) {
        this.user_SaccoName = user_SaccoName;
    }
}
