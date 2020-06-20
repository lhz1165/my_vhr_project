package org.javaboy.vhr.service;

import com.sun.org.apache.regexp.internal.RE;
import org.javaboy.vhr.mapper.MenuMapper;
import org.javaboy.vhr.model.Hr;
import org.javaboy.vhr.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sun.plugin.liveconnect.SecurityContextHelper;

import java.util.List;

/**
 * @author lhzlhz
 * @create 2020/6/20
 */
@Service
public class MenuService {
	@Autowired
	private MenuMapper menuMapper;

	public List<Menu> getMenuByHrId() {
		return menuMapper.getMenusByHrId(((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
	}

	/**
	 * 获取所有权限对应的菜单
	 * @return
	 */
	public List<Menu> getMenuByRole() {
		return menuMapper.getAllMenusWithRole();
	}
}
