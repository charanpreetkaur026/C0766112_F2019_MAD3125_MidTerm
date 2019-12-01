package com.example.c0766112_f2019_mad3125_midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CalculatedDetails extends AppCompatActivity {
    TextView txtSin, txtFullName, txtFirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculated_details);
        //collecting intent
        Intent mIntent = getIntent();
        CRACustomer customer = mIntent.getParcelableExtra("CRACustomer");
        int sin = customer.sinNumber;
        //String firstName = customer.firstName;
        //String fullName = customer.fullName;
        txtSin.setText(sin);
        //txtFirstName.setText(firstName);
        //txtFullName.setText(firstName);


    }
}
