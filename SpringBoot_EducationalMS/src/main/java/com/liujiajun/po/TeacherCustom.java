package com.liujiajun.po;

/**
 * teacher扩展类
 */
public class TeacherCustom extends Teacher {
    //所属院系名
    private String collegeName;
    private String strBirthyear;
    private String strGrade;

    public void setcollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getcollegeName() {
        return collegeName;
    }

	public String getStrBirthyear() {
		return strBirthyear;
	}

	public void setStrBirthyear(String strBirthyear) {
		this.strBirthyear = strBirthyear;
	}

	public String getStrGrade() {
		return strGrade;
	}

	public void setStrGrade(String strGrade) {
		this.strGrade = strGrade;
	}
    
}
