package adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.billy.googlemap_test.R;

import java.util.List;

import model.room;

/**
 * Created by Vu Khac Hoi on 8/23/2017.
 */

public class adapter_room  extends ArrayAdapter<room> {
    Activity context;
    int resource;
    List<room> objects;

    public adapter_room(@NonNull Activity context, int resource, @NonNull List<room> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);
        ImageView img=row.findViewById(R.id.imgmonan);
        room Room= this.objects.get(position);
        img.setImageResource(Room.getIdroom());
        return row;
    }

}
