package org.javaboy.vhr.config.callback;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaboy.vhr.model.RespBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: lhz
 * @date: 2020/6/22
 **/
public class AccessForbiddenHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(new RespBean(403,"权限不足",null)));
        out.flush();
        out.close();

    }
}
