package org.javaboy.vhr.config;


import org.javaboy.vhr.config.callback.*;
import org.javaboy.vhr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private HrService hrService;

	//需要什么角色
	@Autowired
	private MyFilter myFilter;

	//用户有哪些角色
	@Autowired
	private MyDecisionManager decisionManager;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(hrService);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers("/css/**", "/js/**", "/index.html", "/img/**", "/fonts/**", "/favicon.ico", "/verifyCode")
				.antMatchers("/swagger-ui.html")
				.antMatchers("/webjars/**")
				.antMatchers("/v2/**")
				.antMatchers("/swagger-resources/**");

	}

	@Bean
	SessionRegistryImpl sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	LoginFilter loginFilter() throws Exception {
		LoginFilter loginFilter = new LoginFilter();
		loginFilter.setAuthenticationSuccessHandler(new LoginSuccessHandler());
		loginFilter.setAuthenticationFailureHandler(new LoginFaildHandler());
		loginFilter.setAuthenticationManager(authenticationManagerBean());
		loginFilter.setFilterProcessesUrl("/doLogin");
		ConcurrentSessionControlAuthenticationStrategy sessionStrategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
		sessionStrategy.setMaximumSessions(1);
		loginFilter.setSessionAuthenticationStrategy(sessionStrategy);
		return loginFilter;
	}


	/*登录成功或者失败返回json提示*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//登录等处
		http.authorizeRequests()
				//权限拦截器 代替下面的代码
				.withObjectPostProcessor(new AuthenticationPostProcessor(myFilter, decisionManager))
				.and().logout().logoutSuccessHandler(new LogoutSuccessHandler()).permitAll()
				.and().csrf().disable()
				//请求失败之后直接返回json提示
				.exceptionHandling().authenticationEntryPoint(new LoginExceptionHandler());
		//防止多人登录
		http.addFilterAt(new ConcurrentSessionFilter(sessionRegistry(), new MySessionInformationExpiredStrategy()), ConcurrentSessionFilter.class);
		//自定义json格式登录的实现
		http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
		//  12480

	}
}
