package com.dmall.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class Time {
    public  Timestamp datatime(){
        Timestamp t = new Timestamp(System.currentTimeMillis());
        return t;


    }
    public BigDecimal getOffline_num(Double a){

        String a2=Double.toString(a);
        return new BigDecimal(a2);
    }

    public byte[] bytetest(String b){
        if(b==null){
            new Exception("参数是空");
        }
        byte[] sb=b.getBytes();

        return sb;
    }
    public java.sql.Date datatest(){
        Date date=new Date();
        java.sql.Date data1=new java.sql.Date(date.getTime());
        return data1;
    }
    public java.sql.Time timetest(){

        // Date date=new Date();
        long timestamp = System.currentTimeMillis();
        java.sql.Time time=new java.sql.Time(timestamp);
        return time;
    }
    public String yeartest(){
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        return year;

    }
    public java.sql.Timestamp timestamptest(){
        Date date = new Date();
        Timestamp nousedate = new Timestamp(date.getTime());
        return nousedate;
    }

}
