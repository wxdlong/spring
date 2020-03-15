package com.wxdlong.JPA;

import com.wxdlong.entity.World;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

class HelloTest {
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
       // transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void testPersist() {
        World world = new World();
        world.setName("wxdlong");
        world.setAddress("SH");
        world.setAge(18);
        world.setEnd(new Date());

        entityManager.persist(world);
        System.out.println(world);
    }

    @Test
    public void testPersistExceptionWhenIdExist() {
        World world = new World();
        world.setId(99);
        world.setName("wxdlong");
        world.setAddress("SH");
        world.setAge(18);
        world.setEnd(new Date());

        entityManager.persist(world);
        System.out.println(world);
    }


    //Will throw Removing a detached instance Exception.
    @Test
    public void testRemoveException() {
        World world = new World();
        world.setId(1);

        entityManager.remove(world);
        System.out.println(world);
    }

    @Test
    public void testFlush() {
        World world = entityManager.find(World.class, 1);
        world.setAge(77);
        entityManager.flush();
        System.out.println(world);


    }

    @Test
    public void testRefresh() {
        World world = entityManager.find(World.class, 1);
        entityManager.find(World.class,1);//只有一条select SQL,JPA中有类似hibernate的缓存。
        entityManager.refresh(world);
    }

}