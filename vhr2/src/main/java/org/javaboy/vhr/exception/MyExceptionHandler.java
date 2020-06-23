package org.javaboy.vhr.exception;

import org.javaboy.vhr.model.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.sql.*;

/**
 * @author lhzlhz
 * @create 2020/6/22
 */
@RestControllerAdvice
public class MyExceptionHandler {
	@ExceptionHandler(SQLException.class)
	public RespBean mySqlException() {
		return RespBean.error("数据库异常");
	}

}
