package adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.billy.googlemap_test.R;

import java.util.List;

import model.dish;

/**
 * Created by Vu Khac Hoi on 8/23/2017.
 */

public class adapter_dish  extends ArrayAdapter<dish> {
    Activity context;
    int resource;
    List<dish> objects;
    public adapter_dish(@NonNull Activity context, int resource, @NonNull List<dish> objects) {
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
        TextView textView=row.findViewById(R.id.txtten);

        dish Dish= this.objects.get(position);
        img.setImageResource(Dish.getIdmonan());
        textView.setText(Dish.getName().toString());
         return row;
    }
}
