package com.wxdlong.JPA;

import com.wxdlong.entity.World;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import java.util.Date;

class JPQLTest {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;


    @BeforeEach
    public void before() {
        //1. 创建EntityManagerFactory
        entityManagerFactory = Persistence.createEntityManagerFactory("HelloJPA");
        //2. 创建EntityManager

        entityManager = entityManagerFactory.createEntityManager();
        //3. 开启事务
        transaction = entityManager.getTransaction();
        transaction.begin();
    }

    @AfterEach
    public void after() {
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void testMany2OnePersist() {
        Query query = entityManager.createQuery(" from World w where w.id = :id ",World.class);
        World singleResult = (World) query.setParameter("id", 1).getSingleResult();
        System.out.println(singleResult);


    }


}