package org.javaboy.vhr.config;

import org.javaboy.vhr.config.annotation.LoginMember;
import org.javaboy.vhr.model.Hr;
import org.javaboy.vhr.service.HrService;
import org.javaboy.vhr.util.SpringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author: lhz
 * @date: 2020/6/22
 **/
public class TokenArgumentResolver implements HandlerMethodArgumentResolver {

    private HrService hrService;
    /**
     * 入参筛选
     *
     * @param methodParameter 参数集合
     * @return 格式化后的参数
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return (methodParameter.hasParameterAnnotation(LoginMember.class)
                && methodParameter.getParameterType().equals(Hr.class));
    }

    /**
     * @param methodParameter       入参集合
     * @param modelAndViewContainer model 和 view
     * @param nativeWebRequest      web 相关
     * @param webDataBinderFactory  入参解析
     * @return 包装对象
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) {

        // 避免循环引用的问题, 这边采用这种方式初始化 client
        if (hrService == null) {
            hrService = SpringUtils.getBean(HrService.class);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }

        // 处理生态用户注入
        LoginMember memberAnnotation = methodParameter.getParameterAnnotation(LoginMember.class);
        if (memberAnnotation != null) {
            if (memberAnnotation.isFull()) {

                Hr find = hrService.getHrByName(authentication.getName());

                if (find != null) {
                    // 将用户最新的权限更新到 authentication 中
                    //CommonUtils.setAuthorities(authentication, find.getAuthorities());
                }
                return find;
            }
            return authentication.getPrincipal();
        }
        return null;
    }
}