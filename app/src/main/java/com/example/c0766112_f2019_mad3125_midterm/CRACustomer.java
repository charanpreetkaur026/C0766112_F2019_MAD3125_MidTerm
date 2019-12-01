package com.example.c0766112_f2019_mad3125_midterm;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.widget.EditText;

import java.util.Date;

public class CRACustomer implements Parcelable
{
    String sinNumber;
 String firstName;
 String lastName;
 String fullName;
 String gender;
 Date birthDate;
 int age;
 Date filingDate;
 double grossIncome;
 double federalTax;
 double provicialTax;

    public CRACustomer(String sinNumber, String firstName,
                       String lastName, String gender, double grossIncome)
    {
        this.sinNumber = sinNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.gender = gender;
        this.grossIncome = grossIncome;
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
//
//    public Date getBirthDate() {
//        return birthDate;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public Date getFilingDate() {
//        return filingDate;
//    }

    public double getGrossIncome() {
        return grossIncome;
    }
//
//    public double getFederalTax() {
//        return federalTax;
//    }
//
//    public double getProvicialTax() {
//        return provicialTax;
//    }

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

    }
    public CRACustomer(Parcel parcel){
        sinNumber = parcel.readString();
        firstName = parcel.readString();
        lastName = parcel.readString();
        fullName = parcel.readString();
        gender = parcel.readString();
        grossIncome = parcel.readDouble();

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
