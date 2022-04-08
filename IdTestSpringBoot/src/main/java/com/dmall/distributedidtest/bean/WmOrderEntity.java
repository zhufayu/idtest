/**
 * @Author beiping.pei
 * @data 2020.03.05 15:37
 */
package com.dmall.distributedidtest.bean;

import com.dmall.distributedidtest.Enum.Color;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Year;

public class WmOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    //sql bigint类型
    private Long id;
    private Long order_id;
    private int sku_id;
    //sql varchar 类型
    private String ware_name;
    private  String item_num;
    //sql INT类型
    private int ware_num;
    //sql bigint类型
    private int ware_price;
    //sql datatime类型
    private Timestamp created;
    private Timestamp modified;
    //sql bool,boolean,bit类型,true值
   private boolean yn;
    //sql bool,boolean,bit类型 false值
    private boolean wm_sku_id;
    //sql SMALLINT类型
    private int  ware_id;
    //sql MEDIUMINT类型
    private int  wm_ware_id;
    // sql DOUBLE类型
    private double matnr;
    //sql FLOST类型
    private Float ware_weight;
   //sql DECIMAL 类型
    private BigDecimal offline_num;
    private Double offline_num1;
    // sql CHAR类型
    private String webuser_id;
    //sql TINYTEXT类型
    private String coupon_code_amount;
    //sql TEXT类型
    private String catagory_id;
    //sql MEDIUM TEXT类型
    private String ware_tag;
    //sql LONGTEXT 类型
    private String custom_tag;
    //sql TINY BLOB类型
    private String matkl;
    //sql BLOB类型
    private byte[] blobtest;
    //sql MEDIUM BLOB 类型
    private  String meblob;
    //sql LONG BLOB 类型，sqlservce不支持
    private byte[] longblobtest;
    private String longblob1;
    //sql Date 类型
    private Date datatest;
    //sql Time类型
    private Time timetest;
    //sql YEAR类型
    private String yeartest;

    //sql TimeStamp类型
    private java.sql.Timestamp timestamptest;

    //sql json类型，Emoji
    private String dmall_coupon_detail_new;
    private JsonDemo jsonDemo;

    //sql BINARY
    private byte[] bianyrtest;
    private String bianyrtest2;
    //sql VARBINARY
    private  byte[] offline_bar_code;
    private String offline_bar_code2;



   //sql enum类型
    private Color promotion_sub_type;
    //sql set类型
    private String ware_promotion_price;

    public String getWare_promotion_price() {
        return ware_promotion_price;
    }

    public void setWare_promotion_price(String ware_promotion_price) {
        this.ware_promotion_price = ware_promotion_price;
    }

    public Color getPromotion_sub_type() {
        return promotion_sub_type;
    }

    public void setPromotion_sub_type(Color promotion_sub_type) {
        this.promotion_sub_type = promotion_sub_type;
    }





    public byte[] getOffline_bar_code() {
        return offline_bar_code;
    }

    public void setOffline_bar_code(byte[] offline_bar_code) {
        this.offline_bar_code = offline_bar_code;
    }

    public String getOffline_bar_code2() {
        return offline_bar_code2;
    }

    public void setOffline_bar_code2(String offline_bar_code2) {
        this.offline_bar_code2 = offline_bar_code2;
    }



    public byte[] getBianyrtest() {
        return bianyrtest;
    }

    public void setBianyrtest(byte[] bianyrtest) {
        this.bianyrtest = bianyrtest;
    }

    public String getBianyrtest2() {
        return bianyrtest2;
    }

    public void setBianyrtest2(String bianyrtest2) {
        this.bianyrtest2 = bianyrtest2;
    }

    public String getDmall_coupon_detail_new() {
        return dmall_coupon_detail_new;
    }

    public void setDmall_coupon_detail_new(String dmall_coupon_detail_new) {
        this.dmall_coupon_detail_new = dmall_coupon_detail_new;
    }



    public JsonDemo getJsonDemo() {
        return jsonDemo;
    }

    public void setJsonDemo(JsonDemo jsonDemo) {
        this.jsonDemo = jsonDemo;
    }





    public Timestamp getTimestamptest() {
        return timestamptest;
    }

    public void setTimestamptest(Timestamp timestamptest) {
        this.timestamptest = timestamptest;
    }

    public String getYeartest() {
        return yeartest;
    }

    public void setYeartest(String yeartest) {
        this.yeartest = yeartest;
    }

    public Time getTimetest() {
        return timetest;
    }

    public void setTimetest(Time timetest) {
        this.timetest = timetest;
    }

    public Date getDatatest() {
        return datatest;
    }

    public void setDatatest(Date datatest) {
        this.datatest = datatest;
    }

    public byte[] getLongblobtest() {
        return longblobtest;
    }

    public void setLongblobtest(byte[] longblobtest) {
        this.longblobtest = longblobtest;
    }

    public byte[] getBlobtest() {
        return blobtest;
    }

    public void setBlobtest(byte[] blobtest) {
        this.blobtest = blobtest;
    }


    public String getLongblob1() {
        return longblob1;
    }

    public void setLongblob1(String longblob1) {
        this.longblob1 = longblob1;
    }

    public String getMeblob() {
        return meblob;
    }

    public void setMeblob(String meblob) {
        this.meblob = meblob;
    }

    public String getMatkl() {
        return matkl;
    }

    public void setMatkl(String matkl) {
        this.matkl = matkl;
    }

    public String getCustom_tag() {
        return custom_tag;
    }

    public void setCustom_tag(String custom_tag) {
        this.custom_tag = custom_tag;
    }

    public String getWare_tag() {
        return ware_tag;
    }

    public void setWare_tag(String ware_tag) {
        this.ware_tag = ware_tag;
    }

    public String getCatagory_id() {
        return catagory_id;
    }

    public void setCatagory_id(String catagory_id) {
        this.catagory_id = catagory_id;
    }

    public String getCoupon_code_amount() {
        return coupon_code_amount;
    }

    public void setCoupon_code_amount(String coupon_code_amount) {
        this.coupon_code_amount = coupon_code_amount;
    }

    public String getWebuser_id() {
        return webuser_id;
    }

    public void setWebuser_id(String webuser_id) {
        this.webuser_id = webuser_id;
    }

    public Double getOffline_num1() {
        return offline_num1;
    }

    public void setOffline_num1(Double offline_num1) {
        this.offline_num1 = offline_num1;
    }

    public BigDecimal getOffline_num() {
        return offline_num;
    }

    public void setOffline_num(BigDecimal offline_num) {
        this.offline_num = offline_num;
    }

    public Float getWare_weight() {
        return ware_weight;
    }

    public void setWare_weight(Float ware_weight) {
        this.ware_weight = ware_weight;
    }

    public double getMatnr() {
        return matnr;
    }

    public void setMatnr(double matnr) {
        this.matnr = matnr;
    }

    public int getWm_ware_id() {
        return wm_ware_id;
    }

    public void setWm_ware_id(int wm_ware_id) {
        this.wm_ware_id = wm_ware_id;
    }

    public int getWare_id() {
        return ware_id;
    }

    public void setWare_id(int ware_id) {
        this.ware_id = ware_id;
    }

    public boolean isWm_sku_id() {
        return wm_sku_id;
    }

    public void setWm_sku_id(boolean wm_sku_id) {
        this.wm_sku_id = wm_sku_id;
    }
   public Long getOrder_id() {
       return order_id;
   }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isYn() {
        return yn;
    }

    public void setYn(boolean yn) {
        this.yn = yn;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getSku_id() {
        return sku_id;
    }

    public void setSku_id(int sku_id) {
        this.sku_id = sku_id;
    }

    public String getWare_name() {
        return ware_name;
    }

    public void setWare_name(String ware_name) {
        this.ware_name = ware_name;
    }

    public String getItem_num() {
        return item_num;
    }

    public void setItem_num(String item_num) {
        this.item_num = item_num;
    }

    public int getWare_num() {
        return ware_num;
    }

    public void setWare_num(int ware_num) {
        this.ware_num = ware_num;
    }

    public int getWare_price() {
        return ware_price;
    }

    public void setWare_price(int ware_price) {
        this.ware_price = ware_price;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getModified() {
        return modified;
    }

    public void setModified(Timestamp modified) {
        this.modified = modified;
    }


}
