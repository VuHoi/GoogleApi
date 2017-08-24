package model;

import java.io.Serializable;

/**
 * Created by Billy on 8/23/2017.
 */

public class Location implements Serializable {
    private String name;
    private String locate;
    private String phone;

    public Location(String name, String locate, String phone) {
        this.name = name;
        this.locate = locate;
        this.phone = phone;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return name+"\n"+ locate + "\n" +phone;
    }
}
