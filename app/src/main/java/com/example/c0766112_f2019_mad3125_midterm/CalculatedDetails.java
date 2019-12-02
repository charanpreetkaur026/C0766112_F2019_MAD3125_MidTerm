package com.example.c0766112_f2019_mad3125_midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatedDetails extends AppCompatActivity {
    CRACustomer customer;
    TextView txtDsin, txtDfullName, txtDgender, txtDgrossIncome,
            txtDtaxFilingDate, txtDfederalTax, txtDprovincialTax, lblcpp,
            lblEmpInsurance, lblRRSPcontri, lblCfRRSP,
            lblTaxableIncome, lblTaxPaid;
    double cpp = 0, ei = 0;  double rrsp = 0, rrspCf = 0, taxableIncome, federalTax,
            provincialTax, totalTaxPaid;
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
        lblCfRRSP = findViewById(R.id.txt_D_cfRRSP);
        lblTaxableIncome = findViewById(R.id.txt_D_taxableIncome);
        txtDfederalTax = findViewById(R.id.txt_D_federalTax);
        txtDprovincialTax = findViewById(R.id.txt_D_provincialTax);
        lblTaxPaid = findViewById(R.id.txt_D_taxPayed);


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
       if(customer.getRrspContri() > maxRRSP ){
           rrspCf = rrsp - maxRRSP;
       }else{
           rrsp = maxRRSP;
       }
        lblCfRRSP.setText("RRSP Carry forward: \t"+ rrspCf);
       //taxable income
        taxableIncome = (cpp - ei - rrsp);
        //Toast.makeText(this, "(Double)taxableIncome" + taxableIncome, Toast.LENGTH_SHORT).show();
        lblTaxableIncome.setText("Taxable income:\t" + (double) taxableIncome);
        //federal tax
        double calFederal = calcFedralTax();
        txtDfederalTax.setText("Federal Tax: \t" + calFederal);
        // Provincial Tax
        double calProvincial = calcProvincialTax();
        txtDprovincialTax.setText("Provincial Tax:\t" + calProvincial);
        // total tax paid
        double taxpaid = calTaxPaid();
        lblTaxPaid.setText("Total tax Paid:\t" + taxpaid);




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
    public double calcFedralTax(){
        //calculate federal tax

        if(taxableIncome < 12069.00){
            federalTax = 0;
            //taxableIncome = taxableIncome - 12069.00;
        }else{
            federalTax = taxableIncome - 1;
        }
        return federalTax;
    }
    public  double calcProvincialTax(){
        //calculate provincial tax
        return provincialTax;
    }
    public  double calTaxPaid(){
        return totalTaxPaid;
    }

}
