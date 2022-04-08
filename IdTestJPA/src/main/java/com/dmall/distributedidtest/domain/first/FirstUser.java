package com.dmall.distributedidtest.domain.first;

import javax.persistence.*;

@Entity
@Table(name = "f_user")
public class FirstUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
    String name;
    Integer age;
    Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long id) {
        this.orderId = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
