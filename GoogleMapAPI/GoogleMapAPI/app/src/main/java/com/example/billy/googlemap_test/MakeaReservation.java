package com.example.billy.googlemap_test;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

import sqlite.Databasehelper;

public class MakeaReservation extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener{
    EditText edttime;
    Button book;
    int mYear,mMonth,mDay,fyear,fmonth,fday, hour,minute,fhour,fminute;
    TextView txtNameBook,txtPhoneBook;
    Spinner room;
    ArrayList<String>Listroom;
    Databasehelper myDatabase = new Databasehelper(this);
    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makea_reservation);
        myDatabase.Khoitai();
        database=myDatabase.getMyDatabase();
        setTitle("Make Revervation");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        Cursor cursor=database.rawQuery("select * from orderroom",null);
//        cursor.moveToFirst();
//        Toast.makeText(this,cursor.getString(1),Toast.LENGTH_LONG).show();
        addcontrol();
        book=findViewById(R.id.book);
        addEvent();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        else if(item.getItemId()==R.id.admin )
        {

showDialog(1);

        }

        return super.onOptionsItemSelected(item);
    }
    String text="";

    private void addEvent() {

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                ContentValues values=new ContentValues();
                values.put("FullName",txtNameBook.getText().toString());
                values.put("Fhone",txtPhoneBook.getText().toString());
                values.put("Time",edttime.getText().toString());
                values.put("Room",text);
                database.insert("orderroom",null,values);

                builder.setMessage("Book successfully").setTitle("Meassage");



                AlertDialog dialog = builder.create();

                dialog.show();

            }
        });

        edttime.setOnFocusChangeListener(new View.OnFocusChangeListener() {




            public void onFocusChange(View view, boolean b) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MakeaReservation.this,MakeaReservation.this,mYear,mMonth,mDay);
                if(edttime.hasFocus())
                {  datePickerDialog.show();}
            }

        });

        room.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                text=Listroom.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }



    private void addcontrol() {
        edttime=findViewById(R.id.edttime);
        txtNameBook=findViewById(R.id.txtNameBook);
        txtPhoneBook=findViewById(R.id.txtPhoneBook);
        room=findViewById(R.id.cmbRoom);
        Listroom=new ArrayList<>();
        Listroom.add("AJSAI");
        Listroom.add("TSUBAKI");
        Listroom.add("ASAGAO");
        Listroom.add("SAKURA");
        Listroom.add("JURY");
        Listroom.add("JFUJI");
        Listroom.add("MOMIJI");
        Listroom.add("AYAME");
        ArrayAdapter a=new ArrayAdapter(this,android.R.layout.simple_list_item_1,Listroom);
        room.setAdapter(a);

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        fyear=i;fmonth=i1+1;fday=i2;
        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(MakeaReservation.this,MakeaReservation.this,hour,minute, android.text.format.DateFormat.is24HourFormat(this));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        fhour=i;
        fminute=i1;
        edttime.setText(fyear+"-"+fmonth+"-"+fday+" : "+ fhour+" : "+fminute);
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog dialog =null;
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogview;
        AlertDialog.Builder dialogbuilder;
        if(id==1)
        {
            dialogview = inflater.inflate(R.layout.login, null);
            dialogbuilder = new AlertDialog.Builder(this);
            dialogbuilder.setView(dialogview);

            dialog = dialogbuilder.create();
        }
        return dialog;
    }


    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        final AlertDialog alertDialog;
        if(id==1)
        {
            alertDialog = (AlertDialog) dialog;
            Button btndangnhap=  alertDialog.findViewById(R.id.btnDangNhap);
            Button btnHuy=alertDialog.findViewById(R.id.btnHuy);
            TextView txtforgot= alertDialog.findViewById(R.id.txtforgot);
            final EditText edtpass=  alertDialog.findViewById(R.id.edtpass);

                btndangnhap.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
//                        if(edtpass.getText().equals("123456")) {
                            Intent intent = new Intent(MakeaReservation.this, detail_user.class);
                            startActivity(intent);
//                        }
                    }
                });

            btnHuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });
        }
    }
}
