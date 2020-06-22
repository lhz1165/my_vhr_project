package org.javaboy.vhr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.javaboy.vhr.config.annotation.LoginMember;
import org.javaboy.vhr.model.Hr;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("hello test")
@RestController
public class HelloController {

    @ApiOperation("hello1")
    @GetMapping("/employee/basic/hello")
    public String helo1(@LoginMember Hr hr) {
        return "hel"+hr.getName();
    }
    @GetMapping("/employee/advanced/hello")
    public String helo2() {
        return "hello";
    }
    @GetMapping("/personnel/emp/hello")
    public String helo3() {
        return "hel";
    }

}
