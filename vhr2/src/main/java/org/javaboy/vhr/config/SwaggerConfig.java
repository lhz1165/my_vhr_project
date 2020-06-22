package org.javaboy.vhr.config;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Sets;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Operation;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author lhzlhz
 * @create 2020/6/20
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig  {
	@Bean
	public Docket createRestApi(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				//为当前包下controller生成API文档
				.apis(RequestHandlerSelectors.basePackage("org.javaboy.vhr.controller"))
				//为有@Api注解的Controller生成API文档
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
				//为有@ApiOperation注解的方法生成API文档
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("SwaggerUI演示")
				.description("vhr2")
				.contact("macro")
				.version("1.0")
				.build();
	}



}
