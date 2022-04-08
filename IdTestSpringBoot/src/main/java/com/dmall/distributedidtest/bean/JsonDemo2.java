/**
 * @Author beiping.pei
 * @data 2020.03.05 15:37
 */
package com.dmall.distributedidtest.bean;

import java.io.Serializable;

public class JsonDemo2 implements Serializable {
    private static final long serialVersionUID = 1L;
    private  int test1;

    @Override
    public String toString() {
        return "JsonDemo2{" +
                "test1=" + test1 +
                ", test2='" + test2 + '\'' +
                '}';
    }

    private  String test2;

    public int getTest1() {
        return test1;
    }

    public void setTest1(int test1) {
        this.test1 = test1;
    }

    public String getTest2() {
        return test2;
    }

    public void setTest2(String test2) {
        this.test2 = test2;
    }
}
