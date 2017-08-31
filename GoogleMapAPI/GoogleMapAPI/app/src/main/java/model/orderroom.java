package model;

/**
 * Created by Vu Khac Hoi on 8/31/2017.
 */

public class orderroom {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    String phone;
    String time;
    String room;

    public orderroom(String name, String phone, String time, String room) {
        this.name = name;
        this.phone = phone;
        this.time = time;
        this.room = room;
    }
}
