package com.example.root.androidworkshop;

import java.util.GregorianCalendar;

/**
 * Created by root on 9/6/17.
 */

public class Date extends GregorianCalendar {

    public Date(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    public Date(String date) {
        this(Integer.parseInt(date.substring(0, 4)),
                Integer.parseInt(date.substring(4, 6)),
                Integer.parseInt(date.substring(6, 8)));
    }

    public int getDate() { return fields[5]; }

    public int getMonth() {
        return fields[2];
    }

    public int getYear() {
        return fields[1];
    }

    //2017-9-18 to Object Date
    public static Date displayStringToDate(String date){
        String[] temp = date.split("-");
        return new Date(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
    }

    //return in format of 2017-9-18
    public String displayDate(){
        return this.getYear() + "-" + this.getMonth() + "-" + this.getDate();
    }

    //return in format of 20170918
    public String toString(){
        String date = String.valueOf(this.getYear());
        if (this.getMonth() < 10) {
            date += "0" + String.valueOf(this.getMonth());
        } else {
            date += String.valueOf(this.getMonth());
        }

        if (this.getDate() < 10) {
            date += "0" + String.valueOf(this.getDate());
        } else {
            date += String.valueOf(this.getDate());
        }

        return date;
    }
}
