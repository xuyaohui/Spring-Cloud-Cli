package com.teradata.tag.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xuyaohui
 * @date 2018/8/6 0006 下午 2:49
 *
 * 自定义注解
 */


@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MyRequireRole {

    String[] value();
}
