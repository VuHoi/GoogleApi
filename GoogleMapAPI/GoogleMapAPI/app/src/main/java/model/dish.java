package model;

/**
 * Created by Vu Khac Hoi on 8/23/2017.
 */

public class dish {
    public int getIdmonan() {
        return idmonan;
    }

    public void setIdmonan(int idmonan) {
        this.idmonan = idmonan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public dish(int idmonan, String name) {

        this.idmonan = idmonan;
        this.name = name;
    }

    int idmonan;
    String name;
}
