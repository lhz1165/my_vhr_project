package org.javaboy.vhr.config.callback;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author lhzlhz
 * @create 2020/6/20
 */
public class AuthenticationPostProcessor implements ObjectPostProcessor<FilterSecurityInterceptor> {
	private FilterInvocationSecurityMetadataSource myFilter;
	private AccessDecisionManager decisionManager;

	public AuthenticationPostProcessor(FilterInvocationSecurityMetadataSource myFilter, AccessDecisionManager decisionManager) {
		this.myFilter = myFilter;
		this.decisionManager = decisionManager;
	}

	@Override
	public <O extends FilterSecurityInterceptor> O postProcess(O o) {
		o.setSecurityMetadataSource(myFilter);//访问这个页面需要的角色
		o.setAccessDecisionManager(decisionManager);//你是否有这些角色
		return o;
	}
}
