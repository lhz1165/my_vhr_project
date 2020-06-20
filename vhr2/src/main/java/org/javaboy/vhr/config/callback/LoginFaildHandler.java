package org.javaboy.vhr.config.callback;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaboy.vhr.model.RespBean;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lhzlhz
 * @create 2020/6/20
 */
public class LoginFaildHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		RespBean respBean = RespBean.error("登录失败");
		if (e instanceof LockedException) {
			respBean.setMsg("账号被锁");
		} else if (e instanceof CredentialsExpiredException){
			respBean.setMsg("密码过期");
		} else if (e instanceof AccountExpiredException) {
			respBean.setMsg("账户过期");
		} else if (e instanceof DisabledException) {
			respBean.setMsg("账户禁用");
		} else if (e instanceof BadCredentialsException) {
			respBean.setMsg("用户名或密码错误");
		}
		String s = new ObjectMapper().writeValueAsString(respBean);
		out.write(s);
		out.flush();
		out.close();
	}
}
