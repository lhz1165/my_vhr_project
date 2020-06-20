package org.javaboy.vhr.config;

import org.javaboy.vhr.model.Menu;
import org.javaboy.vhr.model.Role;
import org.javaboy.vhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lhzlhz
 * @create 2020/6/20
 */
@Component
public class MyFilter  implements FilterInvocationSecurityMetadataSource {
	@Autowired
	private MenuService menuService;
	AntPathMatcher matcher = new AntPathMatcher();

	/*访问当前url的拦截器，获取这个url需要什么角色*/
	@Override
	public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) o).getRequestUrl();
		//获取所有角色对应的menu
		List<Menu> menuOfRole = menuService.getMenuByRole();
		for (Menu menu : menuOfRole) {
			//匹配规则/employee/basic/**  和要访问的的url /employee/basic/hello
			if (matcher.match(menu.getUrl(), requestUrl)) {
				//这页面需要的角色 然后添加到配置中去
				List<String> roles = menu.getRoles().stream().map(Role::getName).collect(Collectors.toList());
				String[] roleNames=new String[roles.size()];
				System.out.println(requestUrl+":  需要这些权限");
				System.out.println(Arrays.toString(roles.toArray(roleNames)));
				return SecurityConfig.createList(roles.toArray(roleNames));
			}
		}
		return SecurityConfig.createList("ROLE_LOGIN");
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return false;
	}
}
