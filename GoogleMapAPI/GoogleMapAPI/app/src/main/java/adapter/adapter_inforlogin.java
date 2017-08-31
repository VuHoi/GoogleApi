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

import model.inforlogin;

/**
 * Created by Vu Khac Hoi on 8/31/2017.
 */

public class adapter_inforlogin extends ArrayAdapter<inforlogin> {

    Activity context;
    int resource;
    List<inforlogin> objects;
    public adapter_inforlogin(@NonNull Activity context, int resource, @NonNull List<inforlogin> objects) {
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
        ImageView img=row.findViewById(R.id.imgIconlogin);
        TextView textView=row.findViewById(R.id.txtnamelogin);

        inforlogin Inforlogin= this.objects.get(position);
        img.setImageBitmap(Inforlogin.getImage());
        textView.setText(Inforlogin.getName().toString());
        return row;
    }
}
