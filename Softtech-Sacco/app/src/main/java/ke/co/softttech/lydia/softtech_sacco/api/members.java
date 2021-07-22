package ke.co.softttech.lydia.softtech_sacco.api;

public class members {

    private String id;
    private String id_number,fullname,phonenumber,krapin,sacconame;

    public members(String id_number, String fullname, String phonenumber, String krapin, String sacconame) {
        this.id_number = id_number;
        this.fullname = fullname;
        this.phonenumber = phonenumber;
        this.krapin = krapin;
        this.sacconame = sacconame;
    }

    public members(String id, String id_number, String fullname, String phonenumber, String krapin, String sacconame) {
        this.id = id;
        this.id_number = id_number;
        this.fullname = fullname;
        this.phonenumber = phonenumber;
        this.krapin = krapin;
        this.sacconame = sacconame;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getKrapin() {
        return krapin;
    }

    public void setKrapin(String krapin) {
        this.krapin = krapin;
    }

    public String getSacconame() {
        return sacconame;
    }

    public void setSacconame(String sacconame) {
        this.sacconame = sacconame;
    }
}
