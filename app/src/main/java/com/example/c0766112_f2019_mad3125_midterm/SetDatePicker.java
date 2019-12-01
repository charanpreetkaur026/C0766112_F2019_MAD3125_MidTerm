package com.example.c0766112_f2019_mad3125_midterm;

import android.app.DatePickerDialog;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.RequiresApi;

import java.util.Calendar;
import java.util.Locale;

class SetDatePicker implements View.OnFocusChangeListener, DatePickerDialog.OnDateSetListener {

        private EditText txtBirthDate;
        private Calendar myCalendar;
        private Context ctx;

    public SetDatePicker(EditText editText, Context ctx){
            this.txtBirthDate = editText;
            this.txtBirthDate.setOnFocusChangeListener(this);
            myCalendar = Calendar.getInstance();
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)     {
            // this.editText.setText();

            String myFormat = "MMM dd, yyyy"; //In which you need put here
            SimpleDateFormat sdformat = new SimpleDateFormat(myFormat, Locale.US);
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            txtBirthDate.setText(sdformat.format(myCalendar.getTime()));

        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            // TODO Auto-generated method stub
            if(hasFocus){
                new DatePickerDialog(ctx, this, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        }


}
