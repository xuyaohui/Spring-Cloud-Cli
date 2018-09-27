package com.teradata.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFactory {
    private static Logger logger= LoggerFactory.getLogger(LogFactory.class);

    public static void info(String text){
        logger.info(text);
    }

    public static void debug(String text){
        logger.debug(text);
    }
    public static void error(String text){
        logger.error(text);
    }
}
