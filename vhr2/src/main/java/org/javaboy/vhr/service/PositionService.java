package org.javaboy.vhr.service;

import org.javaboy.vhr.mapper.PositionMapper;
import org.javaboy.vhr.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author lhzlhz
 * @create 2020/6/21
 */
@Service
public class PositionService {
	@Autowired
	private PositionMapper positionMapper;

	public List<Position> getAllPositions() {
		return positionMapper.getAllPositions();
	}

	public int addPosition(Position position) {
		position.setEnabled(true);
		position.setCreateDate(new Date());
		return positionMapper.insertSelective(position);
	}
}
