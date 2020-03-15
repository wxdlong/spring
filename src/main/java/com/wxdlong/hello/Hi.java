package com.wxdlong.hello;

import com.wxdlong.bean.MyBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Hi {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("boot.xml");
        MyBean bean = classPathXmlApplicationContext.getBean(MyBean.class);
        System.out.println(bean.getAge());
    }
}
