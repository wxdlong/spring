package com.wxdlong.hello;

import com.wxdlong.bean.MyBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotaionLoad {
    public static void main(String[] args) {
//        Config
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AnnoationConfig.class);
        MyBean bean = applicationContext.getBean(MyBean.class);
        System.out.println(bean.getAge());
    }
}
