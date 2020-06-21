package org.javaboy.vhr.controller;

import io.swagger.annotations.Api;
import org.javaboy.vhr.model.Position;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lhzlhz
 * @create 2020/6/21
 */
@Api("职位")
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {
	@Autowired
	PositionService positionService;
	@GetMapping("/positions")
	public List<Position> getAllPositions() {
		return positionService.getAllPositions();
	}
	@PostMapping("/position")
	public RespBean addPosition(@RequestBody Position position) {
		if (positionService.addPosition(position) == 1) {
			return RespBean.ok("添加成功!");
		}
		return RespBean.error("添加失败!");
	}
}
