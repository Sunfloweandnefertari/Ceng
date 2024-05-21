package com.liujiajun.mapper;

import java.util.List;

import com.liujiajun.po.Vedio;

public interface VedioMapper {

	List<Vedio> findByTId(Integer tid);

	void insert(Vedio vedio);

	List<Vedio> findByPaging();

	Vedio findById(Integer id);

}
