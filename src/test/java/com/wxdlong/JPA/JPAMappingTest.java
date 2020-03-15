package com.wxdlong.JPA;

import com.wxdlong.entity.World;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

class JPAMappingTest {
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
        World world = new World();
        world.setName("wxdlong");
        world.setAddress("SH");
        world.setAge(18);
        world.setEnd(new Date());

        entityManager.persist(world);
        System.out.println(world);
    }

    //默认使用左外链接方式获取N的一凋端的对象和其关联的1对象
    //Fetch LAZY可以修改加载策略。
    @Test
    public void testMany2OneFind() {
        World world = new World();
        world.setId(99);
        world.setName("wxdlong");
        world.setAddress("SH");
        world.setAge(18);
        world.setEnd(new Date());

        entityManager.persist(world);
        System.out.println(world);
    }

    @Test
    public void testMany2OneRemove() {

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

    }

    @Test
    public void testRefresh() {
        World world = entityManager.find(World.class, 1);
        entityManager.find(World.class,1);//只有一条select SQL,JPA中有类似hibernate的缓存。
        entityManager.refresh(world);
    }

}