package org.javaboy.vhr.config;

import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 根据 token 自动注入用户实体类
 *
 * @author abm
 */
public class EnableAuthUserInjectionConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new TokenArgumentResolver());
    }
}
