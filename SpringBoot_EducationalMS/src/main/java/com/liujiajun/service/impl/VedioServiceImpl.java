package com.liujiajun.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.liujiajun.mapper.VedioMapper;
import com.liujiajun.po.TeacherCustom;
import com.liujiajun.po.Vedio;
import com.liujiajun.service.VedioService;
import com.liujiajun.utils.FileUtils;
@Service
public class VedioServiceImpl implements VedioService {

	@Autowired
	private VedioMapper vedioMapper;
	
	@Override
	public List<Vedio> findByTId(Integer tid) {
		
		return vedioMapper.findByTId(tid);
	}

	@Override
	public void addVedio(Vedio vedio, MultipartFile fileUrl) {
		String originalFilename = fileUrl.getOriginalFilename();
		try {
			FileUtils.uploadFile(fileUrl.getBytes(), "d:/vedio/", originalFilename);
		} catch (IOException e) {
			e.printStackTrace();
		}
		vedio.setUrl("d:/vedio/"+originalFilename);
		vedio.setFilename(originalFilename);
		vedio.setTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		vedioMapper.insert(vedio);
		
	}

	@Override
	public List<Vedio> findByPaging(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Vedio> list = vedioMapper.findByPaging();

        return list;
	}

	@Override
	public Vedio findById(String id) {
		// TODO Auto-generated method stub
		return vedioMapper.findById(Integer.parseInt(id));
	}

}
