package com.example.c0766112_f2019_mad3125_midterm;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.widget.EditText;

import java.util.Date;

public class CRACustomer implements Parcelable
{
    String sinNumber, firstName, lastName, fullName, gender;
 String birthDate,filingDate;
 int age;
 double grossIncome, federalTax, provicialTax, empInsurance;
 double rrspContri, rrspCarryForward, taxableIncome, taxPaid;

    public CRACustomer(String sinNumber, String firstName,
                       String lastName, String gender, double grossIncome, double rrspContri, int age, String birthDate, String filingDate)
    {
        this.sinNumber = sinNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.gender = gender;
        this.grossIncome = grossIncome;
        this.rrspContri = rrspContri;
        this.age = age;
        this.birthDate = birthDate;
        this.filingDate = filingDate;
    }


    public String getSinNumber() {
        return sinNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        // eg: KAUR, Charan
        return lastName.toUpperCase() + ", " +
                firstName.substring(0,1).toUpperCase() + firstName.substring(1);
    }
    public String getGender(){
        return  gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setSinNumber(String sinNumber) {
        this.sinNumber = sinNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setFilingDate(String filingDate) {
        this.filingDate = filingDate;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGrossIncome(double grossIncome) {
        this.grossIncome = grossIncome;
    }

    public void setFederalTax(double federalTax) {
        this.federalTax = federalTax;
    }

    public void setProvicialTax(double provicialTax) {
        this.provicialTax = provicialTax;
    }

    public void setEmpInsurance(double empInsurance) {
        this.empInsurance = empInsurance;
    }

    public void setRrspContri(double rrspContri) {
        this.rrspContri = rrspContri;
    }

    public void setRrspCarryForward(double rrspCarryForward) {
        this.rrspCarryForward = rrspCarryForward;
    }

    public void setTaxableIncome(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public void setTaxPaid(double taxPaid) {
        this.taxPaid = taxPaid;
    }

    public int getAge() {
        return age;
    }

    public String getFilingDate() {
        return filingDate;
    }

    public double getGrossIncome() {
        return grossIncome;
    }

    public double getEmpInsurance() {
        return empInsurance;
    }

    public double getRrspContri() {
        return rrspContri;
    }

    public double getRrspCarryForward() {
        return rrspCarryForward;
    }

    public double getTaxableIncome() {
        return taxableIncome;
    }

    public double getTaxPaid() {
        return taxPaid;
    }

    public double getFederalTax() {
        return federalTax;
    }

    public double getProvicialTax() {
        return provicialTax;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sinNumber);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(fullName);
        dest.writeString(gender);
        dest.writeDouble(grossIncome);
        dest.writeDouble(rrspContri);
        dest.writeString(filingDate);
        dest.writeString(birthDate);
        dest.writeDouble(taxableIncome);
        dest.writeDouble(federalTax);
        dest.writeDouble(taxPaid);
        dest.writeDouble(provicialTax);
        dest.writeDouble(rrspCarryForward);

    }
    public CRACustomer(Parcel parcel){
        sinNumber = parcel.readString();
        firstName = parcel.readString();
        lastName = parcel.readString();
        fullName = parcel.readString();
        gender = parcel.readString();
        grossIncome = parcel.readDouble();
        rrspContri = parcel.readDouble();
        filingDate = parcel.readString();
        birthDate = parcel.readString();
        taxableIncome = parcel.readDouble();
        federalTax = parcel.readDouble();
        taxPaid = parcel.readDouble();
        provicialTax = parcel.readDouble();
        rrspCarryForward = parcel.readDouble();

    }
    public  static final Parcelable.Creator<CRACustomer> CREATOR = new Creator<CRACustomer>() {
        @Override
        public CRACustomer createFromParcel(Parcel parcel) {
            return new CRACustomer(parcel);
        }

        @Override
        public CRACustomer[] newArray(int size) {
            return new CRACustomer[size];
        }
    };



}
