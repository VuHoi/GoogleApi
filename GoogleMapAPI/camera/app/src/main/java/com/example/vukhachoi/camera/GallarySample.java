package com.example.vukhachoi.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class GallarySample extends Activity {
    private static final int REQUEST_PERMISSIONS = 100;
    private static final int REQUEST_CAMERA = 100;
    ImageAdapter adapter;
private ArrayList<String> images;
Button btnchup; GridView gallery;
@Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallary_sample);

        gallery = (GridView) findViewById(R.id.galleryGridView);

    if ((ContextCompat.checkSelfPermission(getApplicationContext(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
        if ((ActivityCompat.shouldShowRequestPermissionRationale(GallarySample.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(GallarySample.this,
                Manifest.permission.READ_EXTERNAL_STORAGE))) {

        } else {
            ActivityCompat.requestPermissions(GallarySample.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_PERMISSIONS);
        }
    }else {
        Log.e("Else","Else");
        images = getAllShownImagesPath(this);
    }
adapter=new ImageAdapter(this);
    gallery.setAdapter(adapter);

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {

@Override
public void onItemClick(AdapterView<?> arg0, View arg1,
                        int position, long arg3) {
        if (null != images && !images.isEmpty())
        Toast.makeText(
        getApplicationContext(),
        "position " + position + " " + images.get(position),
        Toast.LENGTH_LONG).show();
        ;

        }
        });


btnchup=findViewById(R.id.btnchup);

btnchup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        ActivityCompat.requestPermissions(GallarySample.this,new String[]{Manifest.permission.CAMERA},REQUEST_CAMERA);

    }
});

}


    private ArrayList<String> getAllShownImagesPath(Activity activity) {
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        ArrayList<String> listOfAllImages = new ArrayList<String>();
        String absolutePathOfImage = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = { MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME };

        cursor = activity.getContentResolver().query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);

            listOfAllImages.add(absolutePathOfImage);
        }
        return listOfAllImages;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


            if(requestCode== REQUEST_PERMISSIONS) {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        getAllShownImagesPath(this);
                    } else {
                        Toast.makeText(GallarySample.this, "The app was not allowed to read or write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                    }
                }
            }
             if(requestCode==REQUEST_CAMERA ) {

                if (grantResults.length > 0 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                }

            }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CAMERA&& resultCode==RESULT_OK&& data!=null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
            MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "fafaf" , "Aefaefe");
            images = getAllShownImagesPath(this);

            adapter=new ImageAdapter(this);
            adapter.notifyDataSetChanged();
            gallery.setAdapter(adapter);
        }
    }

    /**
 * The Class ImageAdapter.
 */
private class ImageAdapter extends BaseAdapter {

    /** The context. */
    private Activity context;

    /**
     * Instantiates a new image adapter.
     *
     * @param localContext
     *            the local context
     */
    public ImageAdapter(Activity localContext) {
        context = localContext;

    }

    public int getCount() {
        return images.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView,
                        ViewGroup parent) {
        ImageView picturesView;
        if (convertView == null) {
            picturesView = new ImageView(context);
            picturesView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            picturesView
                    .setLayoutParams(new GridView.LayoutParams(270, 270));

        } else {
            picturesView = (ImageView) convertView;
        }

        Glide.with(context).load(images.get(position))
                .placeholder(R.drawable.ic_launcher_background).centerCrop()
                .into(picturesView);

        return picturesView;
    }

    /**
     * Getting All Images Path.
     *
     * @param activity
     *            the activity
     * @return ArrayList with images Path
     */

}
}