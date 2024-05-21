package com.liujiajun.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.liujiajun.po.Vedio;

public interface VedioService {

	List<Vedio> findByTId(Integer tid);

	void addVedio(Vedio vedio, MultipartFile fileUrl);

	List<Vedio> findByPaging(Integer page, Integer pageSize);

	Vedio findById(String id);

}
