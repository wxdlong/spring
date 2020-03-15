package com.wxdlong.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hello {

    public static final Logger LOGGER = LoggerFactory.getLogger(Hello.class);

    public static void main(String[] args) {
        LOGGER.info("Hello {}", "world");
    }
}
