package com.liujiajun.po;

import java.math.BigDecimal;

import org.springframework.data.annotation.Transient;

public class Score {

	private Integer id;
	
	private Integer sid;
	private Integer tid;
	private BigDecimal score;
	@Transient
	private String sName;//学生姓名
	
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	
}
