package com.example.vukhachoi.fragment;


import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Vu Khac Hoi on 8/28/2017.
 */

public class MyImage {
    private String title, description, path;
    private Calendar datetime;
    private long datetimeLong;
    protected SimpleDateFormat df = new SimpleDateFormat("MMMM d, yy  h:mm");

    /**
     * Gets title.
     *
     * @return Value of title.
     */
    public String getTitle() { return title; }

    /**
     * Gets datetime.
     *
     * @return Value of datetime.
     */
    public Calendar getDatetime() { return datetime; }

    /**
     * Sets new datetimeLong.
     *
     * @param datetimeLong New value of datetimeLong.
     */

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setDatetime(long datetimeLong) {
        this.datetimeLong = datetimeLong;
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(datetimeLong);
        this.datetime = cal;
    }

    /**
     * Sets new datetime.
     *
     * @param datetime New value of datetime.
     */
    public void setDatetime(Calendar datetime) { this.datetime = datetime; }

    /**
     * Gets description.
     *
     * @return Value of description.
     */
    public String getDescription() { return description; }

    /**
     * Sets new title.
     *
     * @param title New value of title.
     */
    public void setTitle(String title) { this.title = title; }

    /**
     * Gets datetimeLong.
     *
     * @return Value of datetimeLong.
     */
    public long getDatetimeLong() { return datetimeLong; }

    /**
     * Sets new description.
     *
     * @param description New value of description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets new path.
     *
     * @param path New value of path.
     */
    public void setPath(String path) { this.path = path; }

    /**
     * Gets path.
     *
     * @return Value of path.
     */
    public String getPath() { return path; }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override public String toString() {
        return "Title:" + title + "   " + df.format(datetime.getTime()) +
                "\nDescription:" + description + "\nPath:" + path;
    }
}
