package ke.co.softttech.lydia.softtech_sacco;

public class Service {
    private String tittle;
    private int thumbnail;

    public Service() {
    }

    public Service(String tittle, int thumbnail) {
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
