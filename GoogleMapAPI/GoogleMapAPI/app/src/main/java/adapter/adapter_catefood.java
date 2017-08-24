package adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.billy.googlemap_test.R;

import java.util.List;

import model.CateFood;

/**
 * Created by Billy on 8/23/2017.
 */

public class adapter_catefood  extends ArrayAdapter<CateFood> {
    Activity context;
    int resource;
    List<CateFood> objects;

    public adapter_catefood(Activity context, int resource, List<CateFood> objects) {
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

        TextView textView=row.findViewById(R.id.txtNameFood);
        ImageView imageView=row.findViewById(R.id.imageView3);
        final CateFood hoa = this.objects.get(position);
        textView.setText(hoa.getName());
        imageView.setImageResource(hoa.getPicture());
        return row;
    }
}
