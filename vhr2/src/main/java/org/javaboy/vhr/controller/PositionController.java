package org.javaboy.vhr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

	@ApiOperation("查询职位")
	@GetMapping("/")
	public List<Position> getAllPositions() {
		return positionService.getAllPositions();
	}

	@ApiOperation("增加职位")
	@PostMapping("/")
	public RespBean addPosition(@RequestBody Position position) {
		if (positionService.addPosition(position) == 1) {
			return RespBean.ok("添加成功!");
		}
		return RespBean.error("添加失败!");
	}
	@PutMapping("/")
	public RespBean updatePositions(@RequestBody Position position) {
		if (positionService.updatePositions(position) == 1) {
			return RespBean.ok("更新成功!");
		}
		return RespBean.error("更新失败!");
	}

	@DeleteMapping("/{id}")
	public RespBean deletePositionById(@PathVariable Integer id) {
		if (positionService.deletePositionById(id) == 1) {
			return RespBean.ok("删除成功!");
		}
		return RespBean.error("删除失败!");
	}

	@DeleteMapping("/")
	public RespBean deletePositionsByIds(Integer[] ids) {
		if (positionService.deletePositionsByIds(ids) == ids.length) {
			return RespBean.ok("删除成功!");
		}
		return RespBean.error("删除失败!");
	}
}
