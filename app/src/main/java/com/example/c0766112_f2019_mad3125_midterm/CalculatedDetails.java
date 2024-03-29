package com.example.c0766112_f2019_mad3125_midterm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class CalculatedDetails extends AppCompatActivity {
    CRACustomer customer;
    TextView txtDsin, txtDfullName, txtDgender, txtDgrossIncome,
            txtDtaxFilingDate, txtDfederalTax, txtDprovincialTax, lblcpp,
            lblEmpInsurance, lblRRSPcontri, lblCfRRSP,
            lblTaxableIncome, lblTaxPaid, lblAge;
    double cpp = 0, ei = 0;  double rrsp = 0, rrspCf = 0, taxableIncome, federalTax,
            provincialTax, totalTaxPaid;

    NumberFormat formatter = NumberFormat.getCurrencyInstance();

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
        txtDtaxFilingDate = findViewById(R.id.txt_D_taxFilingDate);
        lblAge = findViewById(R.id.txt_D_age);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //collecting intent
        Intent mIntent = getIntent();
        CRACustomer customer = mIntent.getParcelableExtra("CRACustomer");
        double pGrossIncome = customer.getGrossIncome();
        double pRrsp = customer.getRrspContri();
        txtDsin.setText(" SIN: \t" + customer.getSinNumber());
        txtDfullName.setText(" FULL NAME: \t" + customer.getFullName());
        txtDgender.setText(" GENDER: \t" + customer.getGender());
        txtDgrossIncome.setText(" GROSS INCOME: \t" + formatter.format(pGrossIncome));
        lblRRSPcontri.setText("RRSP CONTRIBUTED: \t" + formatter.format(pRrsp));
        txtDtaxFilingDate.setText("TAX FILING DATE: \t" + customer.getFilingDate());
        lblAge.setText(customer.getAge());


    // calculate  cpp
        double grossIncome = customer.getGrossIncome();
        if(grossIncome > 57400.00){
            cpp = (57400.00 * 0.051); //5.10%
        } else {
            cpp = (grossIncome * 0.051);
        }
        lblcpp.setText("CPP CONTRIBUTION IN YEAR:\t" + formatter.format(cpp));
        // calculate employement insurance
        if(grossIncome > 53100){
            ei = (53100 * 0.0162); //1.62%
        }else{
            ei = (grossIncome * (1.62/100));
        }
        lblEmpInsurance.setText("EMPLOYEMENT INSURANCE: \t" + formatter.format(ei));
        // calculate RRSP
        rrsp = customer.getRrspContri();
        double maxRRSP = (grossIncome * 0.18); //18%
       if(rrsp > maxRRSP ){
           rrspCf = rrsp - maxRRSP;
           rrsp = maxRRSP;
       }else{
           rrsp = rrsp;
       }
        lblCfRRSP.setText("RRSP CARRY FORWARD: \t"+ formatter.format(rrspCf));
       //taxable income
        taxableIncome = grossIncome - (cpp + ei + rrsp);

        //Toast.makeText(this, "(Double)taxableIncome" + taxableIncome, Toast.LENGTH_SHORT).show();
        lblTaxableIncome.setText("TAXABLE INCOME:\t" + formatter.format(taxableIncome));
        //federal tax
        double calFederal = calcFedralTax();
        txtDfederalTax.setText("FEDERAL TAX: \t" + formatter.format(calFederal));
        // Provincial Tax
        double calProvincial = calcProvincialTax();
        txtDprovincialTax.setText("PROVINCIAL TAX:\t" + formatter.format(calProvincial));
        // total tax paid
        double taxpaid = calTaxPaid();
        lblTaxPaid.setText("TOTAL TAX PAID:\t" + formatter.format(taxpaid));


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
        if(customer.getGrossIncome() > 57400.00){
            cpp = (57400.00 * 0.051);
        } else {
            cpp = (customer.getGrossIncome() * 0.051);
        }
        return cpp;
    }
    public double calcFedralTax(){
        //calculate federal tax
        double temp = taxableIncome ;
        if(temp <= 12069.00){
            federalTax = 0;//0%
            temp = taxableIncome - 12069.00;
        }
        if(temp >= 12069.01){
            federalTax = (temp * 0.15);//15%
            temp = temp - 35561;
        }
        if(temp >= 47630.01){
            federalTax = (temp * 0.205); //20.50%
            temp = temp - 47628.99;
        }
        if(temp >= 95259.01){
            federalTax = (temp * 0.26); //26%
            temp = temp - 52407.99;
        }
        if (temp >= 147667.01){
            federalTax = (temp * 0.29);//29%
            temp = temp - 62703.99;
        }
        if(temp >= 210371.01){
            federalTax = (temp * 0.33);//33%
            //temp = temp - federalTax;
        }

        return federalTax;
    }
    public  double calcProvincialTax(){
        //calculate provincial tax
        double temp = taxableIncome ;

        if(temp <= 10582.00){
            provincialTax = 0;
            temp = taxableIncome - 10582.00;
        }
        if(temp >= 10582.01){
            provincialTax = (temp * 0.0505); //5.05%
            temp = temp - 33323.99;
        }
        if(temp >= 43906.01){
            provincialTax = (temp * 0.0915); //9.15%
            temp = temp - 43906.99;
        }
        if(temp >= 87813.01){
            provincialTax = (temp * 0.1116); //11.16%
            temp = temp - 62187.99;
        }
        if (temp >= 150000.01){
            provincialTax = (temp * 0.1216);//12.16%
            temp = temp - 69999.99;
        }
        if(temp >= 220000.01){
            provincialTax = (temp * 0.1316);//13.16%

        }
        return provincialTax;
    }
    public  double calTaxPaid(){
        //calculate total tax to pay
        totalTaxPaid = federalTax + provincialTax;
        return totalTaxPaid;
    }

}
