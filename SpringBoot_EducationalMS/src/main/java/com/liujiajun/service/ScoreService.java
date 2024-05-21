package com.liujiajun.service;

import java.util.List;

import com.liujiajun.po.Score;

public interface ScoreService {

	int insert(Score score);

	Score findBySidTid(Integer userid, Integer tid);

	void update(Score s);

	List<Score> findByTId(Integer tid);
}
