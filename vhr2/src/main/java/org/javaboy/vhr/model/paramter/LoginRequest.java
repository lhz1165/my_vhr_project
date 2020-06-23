package org.javaboy.vhr.model.paramter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author: lhz
 * @date: 2020/6/22
 **/
@ApiModel("登录参数")
public class LoginRequest {
    @ApiModelProperty(value = "用户名",example = "admin")
    private String username;
    @ApiModelProperty(value = "密码",example = "\"123\"",dataType = "String")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
