package adapter;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.billy.googlemap_test.R;

import java.util.List;

import sqlite.Databasehelper;

/**
 * Created by Vu Khac Hoi on 8/28/2017.
 */

public class ImageAdapter extends ArrayAdapter<Object> {
    Activity context;

    Databasehelper myDatabase = new Databasehelper(getContext());
    SQLiteDatabase database;
    int resource;
    List<Object> objects;
    public ImageAdapter(@NonNull Activity context, int resource, @NonNull List<Object> objects) {

        super(context, resource, objects);



        this.context=context;
        this.resource=resource;
        this.objects=objects;

        myDatabase.Khoitai();
        database=myDatabase.getMyDatabase();
    }



    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);

        ImageView imghinhanh=row.findViewById(R.id.img);
//        byte[] object = (byte[]) this.objects.get(position);
//        Bitmap bitmap1= BitmapFactory.decodeByteArray(object,0,object.length);
//        imghinhanh.setImageBitmap(bitmap1);
int index= (int) this.objects.get(position);
imghinhanh.setImageResource(R.drawable.bell);



        return row;
    }
}