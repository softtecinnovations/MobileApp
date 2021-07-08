package ke.co.softttech.lydia.softtech_sacco.models;

public class ServiceModel {
    private String tittle;
    private int thumbnail;

    public ServiceModel() {
    }

    public ServiceModel(String tittle, int thumbnail) {
        this.tittle = tittle;
        this.thumbnail = thumbnail;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
