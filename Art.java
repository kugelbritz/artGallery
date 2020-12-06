import java.util.ArrayList;

public class Art extends MyPanel {
    public String title;
    public String medium;
    public String artist;
    public String image;
    public String era;
    static ArrayList<String> piece = new ArrayList<String>(5);

    public Art(String t, String a, String i, String m, String e) {
        this.title = t;
        this.artist = a;
        this.image = i;
        this.medium = m;
        this.era = e;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEra() {
        return era;
    }

    public void setEra(String era) {
        era = era;
    }


}
