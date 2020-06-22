package org.javaboy.vhr.config.callback;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaboy.vhr.model.RespBean;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lhzlhz
 * @create 2020/6/20
 */
public class LoginExceptionHandler implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
		resp.setContentType("application/json;charset=utf-8");
		resp.setStatus(401);
		PrintWriter out = resp.getWriter();
		RespBean respBean = RespBean.error("访问失败");
		if (e instanceof InsufficientAuthenticationException) {
			respBean.setMsg("请求失败");
		}
		out.write( new ObjectMapper().writeValueAsString(respBean));
		out.flush();
		out.close();
	}
}
