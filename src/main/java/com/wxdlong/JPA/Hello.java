package com.wxdlong.JPA;

import com.wxdlong.entity.World;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Hello {
    public static void main(String[] args) {
        //1. 创建EntityManagerFactory
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("HelloJPA");
        //2. 创建EntityManager

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //3. 开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        World world1 = entityManager.find(World.class, 1);
        System.out.println("================================================");

        System.out.println(world1);
        world1 = entityManager.getReference(World.class, 1);
        System.out.println(world1.getClass().getName());
        System.out.println("================================================");
        System.out.println(world1);


        //4. 进行持久化操作
        World world = new World();
        world.setAge(18);
        world.setName("Bingo");

        entityManager.persist(world);
        //5. 提交事务
        transaction.commit();

        //6. 关闭entityManager
        entityManager.close();

        //7. 关闭entityManagerFactory
        entityManagerFactory.close();
     }


}
