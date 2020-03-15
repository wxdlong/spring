package com.wxdlong.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class World {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;


    private String name;

    private Integer age;

    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    private Date start;

    @Column(name = "endTime")
    @Temporal(TemporalType.DATE)
    private Date end;

    @Temporal(TemporalType.TIME)
    private Date birthday;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public String toString() {
        return "World{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", birthday=" + birthday +
                '}';
    }
}
