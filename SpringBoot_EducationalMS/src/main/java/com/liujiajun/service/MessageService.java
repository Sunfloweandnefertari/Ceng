package com.liujiajun.service;

import java.util.List;

import com.liujiajun.po.Message;

public interface MessageService {

	List<Message> findByVid(Integer vid);

	Message findByVidSid(Integer id, Integer userid);

	void update(Message m);

	void insert(Message m);

}
