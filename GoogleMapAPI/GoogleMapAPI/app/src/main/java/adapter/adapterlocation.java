package adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.billy.googlemap_test.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import model.Location;

/**
 * Created by Billy on 8/24/2017.
 */

public class adapterlocation extends ArrayAdapter<Location> {
    Activity context;
    int resource;
    List<Location> objects;

    public adapterlocation(Activity context, int resource, List<Location> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);

        TextView textView=row.findViewById(R.id.txtlocation);
        TextView textView1=row.findViewById(R.id.txtaddress);
        TextView textView2=row.findViewById(R.id.txtphone);
        ImageView imageView=row.findViewById(R.id.photo_ref);


        final Location hoa = this.objects.get(position);
        textView.setText(hoa.getName().toString());
        textView1.setText(hoa.getLocate().toString());
        textView2.setText(hoa.getPhone().toString());
        Picasso.with(getContext()).load("https://maps.googleapis.com/maps/api/place/photo?maxwidth=100&photoreference="+hoa.getPhoto_ref()+"&key=AIzaSyAafUK3_rCTM6esCaZKIj7DNTu8ZkQ6QCw").into(imageView);
        return row;
    }
}
