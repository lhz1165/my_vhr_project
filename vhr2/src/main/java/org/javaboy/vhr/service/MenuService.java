package org.javaboy.vhr.service;

import com.sun.org.apache.regexp.internal.RE;
import org.javaboy.vhr.mapper.MenuMapper;
import org.javaboy.vhr.mapper.MenuRoleMapper;
import org.javaboy.vhr.model.Hr;
import org.javaboy.vhr.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

	@Autowired
	private MenuRoleMapper menuRoleMapper;

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

	public List<Menu> getAllMenus() {
		return menuMapper.getAllMenus();
	}

	public List<Integer> getMidsByRid(Integer rid) {
		return menuMapper.getMidsByRid(rid);
	}

	@Transactional
	public boolean updateMenuRole(Integer rid, Integer[] mids) {
		menuRoleMapper.deleteByRid(rid);
		if (mids == null || mids.length == 0) {
			return true;
		}
		Integer result = menuRoleMapper.insertRecord(rid, mids);
		return result==mids.length;
	}
}
