package com.liujiajun.mapper;

import java.util.List;

import com.liujiajun.po.Message;

public interface MessageMapper {

	List<Message> findByVid(Integer vid);

	Message findByVidSid(Message msg);

	void update(Message m);

	void insert(Message m);

}
