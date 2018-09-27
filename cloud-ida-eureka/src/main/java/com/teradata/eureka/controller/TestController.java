package com.teradata.eureka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xuyaohui on 2018/8/16.
 */

@RestController
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/index")
    public Object index() {
        for (int i = 0; i < 5; i++) {
            logger.debug("debug" + i);
            logger.info("info" + i);
            logger.warn("warn" + i);
            logger.error("error" + i);
        }

        return "success";

    }
}
