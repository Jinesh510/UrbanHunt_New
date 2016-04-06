package jinesh.urbanhunt_new.model;

/**
 * Created by Jinesh on 26/03/16.
 */
public class Sectors {

    private int id;
    private String label;
    private String background;

    public String getBackgroundImg() {
        return background;
    }

    public void setBackgroundImg(String backgroundImg) {
        this.background = backgroundImg;
    }

    public Sectors(int id, String label,String background) {
        this.id = id;
        this.label = label;
        this.background = background;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
