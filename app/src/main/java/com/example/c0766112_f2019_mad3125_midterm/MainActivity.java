package com.example.c0766112_f2019_mad3125_midterm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    CRACustomer customer;

    private TextView txtTitle;
    private EditText edtSinNumber;
    private EditText edtFirstName;
    private EditText edtLastName;
    private TextView txtFullName;
    private EditText txtBirthDate;
    private TextView txtGender;
    private RadioGroup rgGender;
    private RadioButton rdMale;
    private RadioButton rdFemale;
    private RadioButton rdOther;
    private Button btnShow;
    private Calendar calendar;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtSinNumber = findViewById(R.id.edtSin_number);
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        txtFullName = findViewById(R.id.txtFullName);
        txtGender = findViewById(R.id.txtGender);
        rgGender = findViewById(R.id.rgGender);
        btnShow = findViewById(R.id.btnShow);
        txtBirthDate = findViewById(R.id.txtBirthDate);
        SetDatePicker fromDate = new SetDatePicker(txtBirthDate, this);
    btnShow.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        customer = new CRACustomer(edtSinNumber.getText());
        Intent mIntent = new Intent(MainActivity.this, CalculatedDetails.class);
        mIntent.putExtra("CRACustomer", customer);
        startActivity(mIntent);
    }
    });

    };

    }


