package org.javaboy.vhr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.javaboy.vhr.model.Menu;
import org.javaboy.vhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lhzlhz
 * @create 2020/6/20
 */
@Api("菜单")
@RestController
@RequestMapping("/system/config")
public class SystemConfigController {
	@Autowired
	private MenuService menuService;

	@ApiOperation("获取菜单")
	@GetMapping("/menu")
	public List<Menu> getMenuByHrId() {
		return menuService.getMenuByHrId();
	}


}
