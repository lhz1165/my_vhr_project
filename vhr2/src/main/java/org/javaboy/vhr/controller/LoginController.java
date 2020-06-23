package org.javaboy.vhr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.paramter.LoginRequest;
import org.javaboy.vhr.util.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Api("登录")
@RestController
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 自己定义的登录接口，如果你不显示的写出来也可以 springsecurity可以有自己的认证
     * @param loginRequest
     * @return
     */
    @ApiOperation("登录接口")
    @PostMapping("/doLogin")
    public RespBean login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return RespBean.ok("登录成功");
    }





    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        VerificationCode code = new VerificationCode();
        BufferedImage image = code.getImage();
        String text = code.getText();
        HttpSession session = request.getSession(true);
        session.setAttribute("verify_code", text);
        VerificationCode.output(image,resp.getOutputStream());
    }
}
