package org.javaboy.vhr.config.callback;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaboy.vhr.model.Hr;
import org.javaboy.vhr.model.RespBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lhzlhz
 * @create 2020/6/20
 */
public class LoginSuccessHandler  implements AuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp,
										Authentication authentication) throws IOException {
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		Hr hr = (Hr) authentication.getPrincipal();
		RespBean respBean = RespBean.ok("登录成功", hr);
		String s = new ObjectMapper().writeValueAsString(respBean);

		out.write(s);
		out.flush();
		out.close();
	}
}
