/**
 * @Author beiping.pei
 * @data 2020.03.05 15:37
 */
package com.dmall.distributedidtest.bean;


public class Location {
    private String lat; // 经度
    private String lng; // 纬度

    public Location() {
    }

    /**
     * 
     * @param lat
     *            经度
     * @param lng
     *            纬度
     */
    public Location(String lat, String lng) {
        setLat(lat);
        setLng(lng);
    }

    /**
     * 转化为location对象，简单点，字符串处理
     * 
     * @param pointString
     *            mysql查出来的point字段字符串形式point(10.3 23.2)
     */
    public Location parse(String pointString) {
        String[] latLng = pointString.toLowerCase().replaceAll("point\\(", "").replaceAll("\\)", "")
                .split(" ");
        setLat(latLng[0]);
        setLng(latLng[1]);
        return this;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "point(" + lat + " " + lng + ")";
    }

}
