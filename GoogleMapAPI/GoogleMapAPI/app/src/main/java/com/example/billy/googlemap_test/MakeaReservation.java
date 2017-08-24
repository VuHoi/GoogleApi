package com.example.billy.googlemap_test;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class MakeaReservation extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener{
EditText edttime;
Button book;
int mYear,mMonth,mDay,fyear,fmonth,fday, hour,minute,fhour,fminute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makea_reservation);
        addcontrol();
        book=findViewById(R.id.book);
        addEvent();
    }

    String text;

    private void addEvent() {

book.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());


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





    }



    private void addcontrol() {
        edttime=findViewById(R.id.edttime);

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
}
