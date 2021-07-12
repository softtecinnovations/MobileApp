package ke.co.softttech.lydia.softtech_sacco.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonModel {
//    @SerializedName("FullName")
//    @Expose
    String fullName;
    String kraPin;
    String saccoName;
    String Id;
    String PhoneNumber;

    public PersonModel() {
    }

    public PersonModel(String fullName, String kraPin, String saccoName, String id, String phoneNumber) {
        this.fullName = fullName;
        this.kraPin = kraPin;
        this.saccoName = saccoName;
        this.Id = id;
        this.PhoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getKraPin() {
        return kraPin;
    }

    public void setKraPin(String kraPin) {
        this.kraPin = kraPin;
    }

    public String getSaccoName() {
        return saccoName;
    }

    public void setSaccoName(String saccoName) {
        this.saccoName = saccoName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }

    @Override
    public java.lang.String toString() {
        return "PersonModel{" +
                "fullName='" + fullName + '\'' +
                ", kraPin='" + kraPin + '\'' +
                ", saccoName='" + saccoName + '\'' +
                ", Id=" + Id +
                ", PhoneNumber=" + PhoneNumber +
                '}';
    }
}
