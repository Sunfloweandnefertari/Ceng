package com.liujiajun.mapper;

import java.util.List;

import com.liujiajun.po.Score;

public interface ScoreMapper {
	int insert(Score score);

	Score findBySidTid(Score score);

	void update(Score s);

	List<Score> findByTId(Integer tid);
}
