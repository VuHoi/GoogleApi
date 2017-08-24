package model;

/**
 * Created by Billy on 8/23/2017.
 */

public class CateFood {
    private String name;
    private int picture;

    public CateFood(String name, int picture) {
        this.name = name;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }
}
