/**
 * @Author beiping.pei
 * @data 2020.03.05 15:37
 */
package com.dmall.distributedidtest.util;

import com.alibaba.fastjson.JSON;
import com.dmall.distributedidtest.Enum.Color;
import com.dmall.distributedidtest.bean.JsonDemo;
import com.dmall.distributedidtest.bean.WmOrderEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

  public WmOrderEntity util(WmOrderEntity wmOrderEntityu){
      Time time = new Time();
      wmOrderEntityu.setCreated(time.datatime());
      wmOrderEntityu.setModified(time.datatime());
      wmOrderEntityu.setDatatest(time.datatest());
      wmOrderEntityu.setTimetest(time.timetest());
      wmOrderEntityu.setYeartest(time.yeartest());
      wmOrderEntityu.setTimestamptest(time.timestamptest());
     // wmOrderEntityu.setBianyrtest(time.bytetest(wmOrderEntityu.getBianyrtest2()));
      wmOrderEntityu.setBianyrtest(time.bytetest(wmOrderEntityu.getOffline_bar_code2()));
      wmOrderEntityu.setPromotion_sub_type(Color.RED);
      JsonDemo jsonDemo = new JsonDemo();
      jsonDemo.setProperties(wmOrderEntityu.getJsonDemo().getProperties());
      jsonDemo.setEmoji(wmOrderEntityu.getJsonDemo().getEmoji());
      jsonDemo.setJsonDemo2(wmOrderEntityu.getJsonDemo().getJsonDemo2());
      String jsonstring = JSON.toJSONString(jsonDemo);
      wmOrderEntityu.setDmall_coupon_detail_new(jsonstring);
      wmOrderEntityu.setOffline_num(time.getOffline_num(wmOrderEntityu.getOffline_num1()));
      wmOrderEntityu.setLongblobtest(time.bytetest(wmOrderEntityu.getLongblob1()));
        return  wmOrderEntityu;
  }

}
