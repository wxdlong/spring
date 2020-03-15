package com.wxdlong.entity;

import javax.persistence.*;


@Table(name = "JPA_Orders")
@Entity
public class Order {

    @Id
    private Integer num;
    private Integer price;
    private String name;

    @JoinColumn(name = "fworld_id")
    @ManyToOne
    private World world;


    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
