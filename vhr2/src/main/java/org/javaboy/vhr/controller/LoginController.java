package org.javaboy.vhr.controller;

import org.javaboy.vhr.model.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    /*未登录给的提示*/
    @GetMapping("/login")
    public RespBean login() {
        return RespBean.error("未登录");
    }
}
