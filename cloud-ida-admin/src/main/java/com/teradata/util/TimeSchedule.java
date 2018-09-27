package com.teradata.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Yun
 * @Description: 定时任务
 * @Date: Created in 2018-01-12 14:17
 */
@Component
public class TimeSchedule {
    /**
     * 定时任务，每个一分钟打印当前时间
     */
    @Scheduled(fixedRate = 1*60*1000)
    public void printTime(){
        LogFactory.info("当前时间为:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
