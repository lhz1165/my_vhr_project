package org.javaboy.vhr.config;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.javaboy.vhr.model.Hr;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author lhzlhz
 * @create 2020/6/20
 * 判断当前角色是否拥有MyFilter.getAttributes()放入的角色
 */
@Component
public class MyDecisionManager implements AccessDecisionManager {
	@Override
	public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
		for (ConfigAttribute attribute : collection) {
			String role = attribute.getAttribute();
			//||"ROLE_admin" .equals(role)
			if ("ROLE_LOGIN" .equals(role)) {
				if (authentication instanceof AnonymousAuthenticationToken) {
					throw new AccessDeniedException("尚未登录，请登录");
				}else {
					return;
				}
			}
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			for (GrantedAuthority authority : authorities) {
				System.out.println(authority.getAuthority());
				if (authority.getAuthority().equals(role)) {
					return;
				}
			}
		}
		System.out.println("角色都没有");
		throw new AccessDeniedException("权限不足");

	}

	@Override
	public boolean supports(ConfigAttribute configAttribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}
}
