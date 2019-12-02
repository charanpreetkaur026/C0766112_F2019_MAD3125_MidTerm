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
        double grossIncome = customer.getGrossIncome();
        if(grossIncome > 57400.00){
            cpp = (57400.00 * 0.051); //5.10%
        } else {
            cpp = (grossIncome * 0.051);
        }
        lblcpp.setText("CPP COntribution in Year:\t" + cpp);
        // calculate employement insurance
        if(grossIncome > 53100){
            ei = (53100 * 0.0162); //1.62%
        }else{
            ei = (grossIncome * (1.62/100));
        }
        lblEmpInsurance.setText("Employeement Insurance: \t" + ei);
        // calculate RRSP
        rrsp = customer.getRrspContri();
        double maxRRSP = (grossIncome * 0.18); //18%
       if(rrsp > maxRRSP ){
           rrspCf = rrsp - maxRRSP;
           rrsp = maxRRSP;
       }else{
           rrsp = rrsp;
       }
        lblCfRRSP.setText("RRSP Carry forward: \t"+ rrspCf);
       //taxable income
        taxableIncome = grossIncome - (cpp + ei + rrsp);
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
        if(taxableIncome < 12069.00){
            federalTax = 0;
            temp = taxableIncome - 12069.00;
        }else if(temp < 47630.00){
            federalTax = (temp * 0.15);
            temp = temp - federalTax;
        }else if(temp < 95259.00){
            federalTax = (temp * 0.205); //20.50%
            temp = temp - federalTax;
        }else if(temp < 147667.00){
            federalTax = (temp * 0.26); //26%
            temp = temp - federalTax;
        }else if (temp < 210371.00){
            federalTax = (temp * 0.29);//29%
            temp = temp - federalTax;
        }else{
            federalTax = (temp * 0.33);//33%
            temp = temp - federalTax;
        }
        return federalTax;
    }
    public  double calcProvincialTax(){
        //calculate provincial tax
            double provincialTax=0.0;

            double first_slab_perc=5.05;
            double first_slab=33324;

            double second_slab_perc=9.15;
            double second_slab=43907;

            double third_slab_perc=11.16;
            double third_slab=62187;

            double fourth_slab_perc=12.16;
            double fourth_slab=70000;

            double final_slab=0.01;
            double final_slab_perc=13.16;
            taxableIncome=taxableIncome-10582.00;
            if(taxableIncome<=first_slab) {
                provincialTax = (first_slab * first_slab_perc) / 100;
                taxableIncome = taxableIncome - first_slab;
            }

            if(taxableIncome<=second_slab) {
                provincialTax = (second_slab * second_slab_perc) / 100;
                taxableIncome = taxableIncome - second_slab;
            }
            if(taxableIncome<=third_slab) {
                provincialTax = (third_slab * third_slab_perc) / 100;
                taxableIncome = taxableIncome - third_slab;
            }
            if(taxableIncome<=fourth_slab) {
                provincialTax = (fourth_slab * fourth_slab_perc) / 100;
                taxableIncome = taxableIncome - fourth_slab;
            }
            if(taxableIncome<=final_slab) {
                provincialTax=(final_slab * final_slab_perc)/100;
            }
            return provincialTax;

    }
    public  double calTaxPaid(){
        return totalTaxPaid;
    }

}
