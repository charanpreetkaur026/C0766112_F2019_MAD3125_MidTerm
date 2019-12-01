package com.example.c0766112_f2019_mad3125_midterm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class CalculatedDetails extends AppCompatActivity {
    CRACustomer customer;
    TextView txtDsin, txtDfullName, txtDgender, txtDgrossIncome,
            txtDtaxFilingDate, txtDfederalTax, txtDprovincialTax, lblcpp,
            lblEmpInsurance, lblRRSPcontri, lblCfRRSP,
            lblTaxableIncome, lblTaxPaid;
    double cpp = 0, ei = 0;  double rrsp = 0, rrspRem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculated_details);
        txtDsin = findViewById(R.id.txt_D_SIN);
        txtDfullName = findViewById(R.id.txt_D_fullName);
        txtDgender =   findViewById(R.id.txt_D_Gender);
        txtDgrossIncome = findViewById(R.id.txt_D_grossIncome);
        lblRRSPcontri = findViewById(R.id.txt_D_RRSPContri);
        lblcpp = findViewById(R.id.txt_D_Cpp);
        lblEmpInsurance = findViewById(R.id.txt_D_empInsurance);


        //collecting intent
        Intent mIntent = getIntent();
        CRACustomer customer = mIntent.getParcelableExtra("CRACustomer");

        txtDsin.setText(" SIN: \t" + customer.getSinNumber());
        txtDfullName.setText(" FULL NAME: \t" + customer.getFullName());
        txtDgender.setText(" GENDER: \t" + customer.getGender());
        txtDgrossIncome.setText(" GROSS INCOME: \t" + customer.getGrossIncome());
        lblRRSPcontri.setText("RRSP Contributed: \t" + customer.getRrspContri());
    // calculate  cpp
        if(customer.getGrossIncome() > 57000.00){
            cpp = (57000.00 * (5.10 / 100));
        } else {
            cpp = (customer.getGrossIncome() * (5.10 / 100));
        }
        lblcpp.setText("CPP COntribution in Year:\t" + cpp);
        // calculate employement insurance
        if(customer.getGrossIncome() > 53100){
            ei = (53100 * (1.62 / 100));
        }else{
            ei = (customer.getGrossIncome() * (1.62/100));
        }
        lblEmpInsurance.setText("Employeement Insurance: \t" + ei);
        // calculate RRSP
        rrsp = customer.getRrspContri();
        double maxRRSP = (customer.getGrossIncome() * (18 /100));
       if(customer.getRrspContri() < maxRRSP ){
           rrsp = customer.getRrspContri();
       }else{
           rrspRem = rrsp - maxRRSP;
           rrsp = maxRRSP;


       }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    public double calcCpp(){
        // calculate  cpp
        if(customer.getGrossIncome() > 57000.00){
            cpp = (57000.00 * (5.10 / 100));
        } else {
            cpp = (customer.getGrossIncome() * (5.10 / 100));
        }
        return cpp;
    }

}
