package org.javaboy.vhr.config.annotation;

import java.lang.annotation.*;

/**
 * 请求的方法参数 LoginMember 上添加该注解，则注入当前登录人信息
 * 例1：public void test(@LoginMember UmsMember ums) // 只有username 和 roles
 * 例2：public void test(@LoginMember(isFull = true) UmsMember ums) //能获取SysUser对象的所有信息
 *
 * @author abm
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginMember {
    /**
     * 是否查询SysUser对象所有信息，true则通过rpc接口查询
     */
    boolean isFull() default true;
}
