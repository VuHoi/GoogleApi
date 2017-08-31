package model;

import android.graphics.Bitmap;

/**
 * Created by Vu Khac Hoi on 8/31/2017.
 */

public class inforlogin {

    String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    public inforlogin(String name, Bitmap image) {
        Name = name;
        Image = image;
    }

    public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap image) {
        Image = image;
    }

    Bitmap Image;
}
