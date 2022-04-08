/**
 * @Author beiping.pei
 * @data 2020.03.05 15:37
 */
package com.dmall.distributedidtest.bean;

import java.io.Serializable;

public class JsonDemo implements Serializable {
    private static final long serialVersionUID = 1L;
    private  String emoji;

    @Override
    public String toString() {
        return "JsonDemo{" +
                "emoji='" + emoji + '\'' +
                ", properties='" + properties + '\'' +
                ", jsonDemo2=" + jsonDemo2 +
                '}';
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    private String properties;
    private JsonDemo2 jsonDemo2;

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }



    public JsonDemo2 getJsonDemo2() {
        return jsonDemo2;
    }

    public void setJsonDemo2(JsonDemo2 jsonDemo2) {
        this.jsonDemo2 = jsonDemo2;
    }


}
