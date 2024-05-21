package com.liujiajun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liujiajun.mapper.MessageMapper;
import com.liujiajun.mapper.StudentMapper;
import com.liujiajun.po.Message;
import com.liujiajun.po.Student;
import com.liujiajun.service.MessageService;
@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public List<Message> findByVid(Integer vid) {
		List<Message> list = messageMapper.findByVid(vid);
		if(list!=null && list.size()>0) {
			for (Message message : list) {
				Student stu = studentMapper.selectByPrimaryKey(message.getSid());
				message.setsName(stu.getUsername());
			}
		}
		return list;
	}

	@Override
	public Message findByVidSid(Integer id, Integer userid) {
		Message msg = new Message();
		msg.setVid(id);
		msg.setSid(userid);
		return messageMapper.findByVidSid(msg);
	}

	@Override
	public void update(Message m) {
		messageMapper.update(m);
		
	}

	@Override
	public void insert(Message m) {
		messageMapper.insert(m);
		
	}

}
