package com.example.c0766112_f2019_mad3125_midterm;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;

import java.util.Date;

public class CRACustomer implements Parcelable
{
    int sinNumber;
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

    public CRACustomer(int sinNumber, String firstName, String lastName, String gender)
    {
        this.sinNumber = sinNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.gender = gender;
    }


    public int getSinNumber() {
        return sinNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + lastName;
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
//
//    public double getGrossIncome() {
//        return grossIncome;
//    }
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
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(sinNumber);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(fullName);

    }
    public CRACustomer(Parcel parcel){

    }
    public  static final Parcelable.Creator<CRACustomer> CREATOR = new Creator<CRACustomer>() {
        @Override
        public CRACustomer createFromParcel(Parcel parcel) {
            return new CRACustomer(parcel);
        }

        @Override
        public CRACustomer[] newArray(int size) {
            return new CRACustomer[0];
        }
    };

}
