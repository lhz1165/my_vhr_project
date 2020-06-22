package org.javaboy.vhr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lhzlhz
 * @create 2020/6/22
 * 为前端访问的头像创建虚拟路径比如前端输入src=“/image/head1” 实际上对应C:\Users\lhz\Desktop\head\head1
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/image/**").addResourceLocations("file:C:\\Users\\lhz\\Desktop\\head\\");
	}
}

