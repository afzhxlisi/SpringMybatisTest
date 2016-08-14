package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.Teacher;
import com.cn.hnust.pojo.TeacherTExample;

public interface ITeacherService {
	public Teacher getTeacherById(int userId);

	List<Teacher> getTeacherByExample(TeacherTExample userEx);


	void testForInitAge();


	void testRequireNew(boolean isThrow, boolean isThrow0, boolean isThrow1);

	void testRequireNew1(boolean isThrow1);
}
