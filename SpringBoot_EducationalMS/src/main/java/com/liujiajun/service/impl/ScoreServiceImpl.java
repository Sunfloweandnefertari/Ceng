package com.liujiajun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liujiajun.mapper.ScoreMapper;
import com.liujiajun.mapper.StudentMapper;
import com.liujiajun.po.Score;
import com.liujiajun.po.Student;
import com.liujiajun.service.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService{

	@Autowired
	private ScoreMapper scoreMapper;
	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public int insert(Score score) {
		
		return scoreMapper.insert(score);
	}

	@Override
	public Score findBySidTid(Integer userid, Integer tid) {
		Score s = new Score();
		s.setSid(userid);
		s.setTid(tid);
		return scoreMapper.findBySidTid(s);
	}

	@Override
	public void update(Score s) {
		scoreMapper.update(s);
		
	}

	@Override
	public List<Score> findByTId(Integer tid) {
		
		List<Score> list = scoreMapper.findByTId(tid);
		if(list!=null && list.size()>0) {
			for (Score score : list) {
				Student stu = studentMapper.selectByPrimaryKey(score.getSid());
				score.setsName(stu.getUsername());
				
			}
		}
		
		return list;
	}

}
