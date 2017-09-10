package model;

import java.io.Serializable;

/**
 * Created by Billy on 8/23/2017.
 */

public class Location implements Serializable {
    private String name;
    private String locate;
    private String phone;
    private String photo_ref;
    private double lati,longti;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public double getLati() {
        return lati;
    }

    public void setLati(double lati) {
        this.lati = lati;
    }

    public double getLongti() {
        return longti;
    }

    public void setLongti(double longti) {
        this.longti = longti;
    }

    public String getPhone() {
        return phone;

    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto_ref() {
        return photo_ref;
    }

    public void setPhoto_ref(String photo_ref) {
        this.photo_ref = photo_ref;
    }


    public Location(String name, String locate, String phone, String photo_ref) {

        this.name = name;
        this.locate = locate;
        this.phone = phone;
        this.photo_ref = photo_ref;
    }

    public Location(String name, String locate, String phone, String photo_ref, double lati, double longti) {
        this.name = name;
        this.locate = locate;
        this.phone = phone;
        this.photo_ref = photo_ref;
        this.lati = lati;
        this.longti = longti;
    }
}
