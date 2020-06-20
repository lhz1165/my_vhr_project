package org.javaboy.vhr.config.callback;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaboy.vhr.model.RespBean;
import org.springframework.security.core.Authentication;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lhzlhz
 * @create 2020/6/20
 */
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {
	@Override
	public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.write(new ObjectMapper().writeValueAsString(RespBean.ok("退出成功")));

		out.flush();
		out.close();

	}
}
