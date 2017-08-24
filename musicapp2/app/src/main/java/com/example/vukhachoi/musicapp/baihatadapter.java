package com.example.vukhachoi.musicapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Vu Khac Hoi on 8/23/2017.
 */


    public class baihatadapter extends ArrayAdapter {
        Activity context=null;
        ArrayList<baihat> myArray=null;
        int layoutId;
        public baihatadapter(@NonNull Activity context, int resource, @NonNull ArrayList<baihat>arr) {
            super(context, resource, arr);
            this.context=  context;
            this.layoutId=resource;
            this.myArray=arr;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater=
                    context.getLayoutInflater();
            convertView=inflater.inflate(layoutId, null);

            if(myArray.size()>0 && position>=0)
            {
                final TextView txtTenbaihat=convertView.findViewById(R.id.txtname);
                final TextView txttacgia=convertView.findViewById(R.id.txttacgia);
                baihat Baihat=this.myArray.get(position);

                txtTenbaihat.setText(Baihat.get_tenbaihat());
                txttacgia.setText(Baihat.get_tentacgia());
            }
            return convertView;
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
}

