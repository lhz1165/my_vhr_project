package org.javaboy.vhr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/employee/basic/hello")
    public String helo1() {
        return "hel";
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
