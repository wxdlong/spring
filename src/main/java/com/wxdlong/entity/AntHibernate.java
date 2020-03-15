package com.wxdlong.entity;

import javax.persistence.*;


@NamedQuery(name = "NameQuery1", query = "from AntHibernate ant where ant.name = :name")
@Entity
public class AntHibernate {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id;

    @Column()
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AntHibernate{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
