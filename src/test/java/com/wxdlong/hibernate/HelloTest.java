package com.wxdlong.hibernate;

import com.wxdlong.entity.AntHibernate;
import com.wxdlong.entity.World;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HelloTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(HelloTest.class);
    private SessionFactory sessionFactory;


    @BeforeEach
    void setUp() {
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
    }

    @AfterEach
    void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @Test
    public void hello() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        World world = new World();
        world.setAge(11);
        world.setName("bing");
        world.setEnd(new Date());
        world.setAddress("上海长宁");
        world.setStart(new Date());
        session.save(world);
        transaction.commit();
        session.close();

        // now lets pull events from the database and list them
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        List<World> result = session.createQuery("from World ").list();
        for (World event : result) {
            LOGGER.info("world {}", event);
        }
        transaction.commit();
        session.close();
    }

    @Test
    public void helloAnt() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        AntHibernate antHibernate = new AntHibernate();
        antHibernate.setName("antHibernate");

        session.save(antHibernate);

        /**
         * 以下两个查询会对数据库查询两次。
         */
        antHibernate = session.createQuery("from AntHibernate ant where ant.name ='antHibernate'", AntHibernate.class).getSingleResult();
        antHibernate = session.createQuery("from AntHibernate ant where ant.name ='antHibernate'", AntHibernate.class).getSingleResult();

        LOGGER.info("Select antHibernate: {}", antHibernate);
        session.getTransaction().commit();
        session.close();
    }


    /**
     * NamedQuery 依旧没有一级缓存？
     */
    @Test
    public void helloAnt2() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        AntHibernate antHibernate = new AntHibernate();
        antHibernate.setName("antHibernate");

        session.save(antHibernate);

        /**
         * 以下两个查询会对数据库查询两次。
         */
        antHibernate = session.createNamedQuery("NameQuery1", AntHibernate.class).setParameter("name", "antHibernate").getSingleResult();
        AntHibernate antHibernate2 = session.createNamedQuery("NameQuery1", AntHibernate.class).setParameter("name", "antHibernate").getSingleResult();

        LOGGER.info("Select antHibernate: {}, 两个查询对象相等否？ {}", antHibernate,antHibernate == antHibernate2);

        /**
         * 1。 transaction commit 之前会先调用session的flush方法，再提交事务
         * 2。 flush() 方法可能会发送SQL语句，但不会提交事务
         * 3。 注意：在未提交事务，或显示调用session.flush的时候，也是有可能会进行flush操作的
         *     a: 执行HQL或QBC查询，会先进行FLUSH操作以得到数据表的最新数据
         *     b: 若记录的ID是由底层数据库使用自增方式生成的，则调用save()方法后，会立即发送insert语句。
         *        因为save方法后必须保证对象的ID存在有效！
         */

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testRefresh(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
    }
}