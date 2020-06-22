package org.javaboy.vhr.config.annotation;

import org.javaboy.vhr.config.EnableAuthUserInjectionConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在启动类上添加该注解来----开启自动登录用户对象注入
 * 根据 token 自动注入用户实体类
 *
 * @author mall
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(EnableAuthUserInjectionConfig.class)
public @interface EnableAuthUserInjection {
}
