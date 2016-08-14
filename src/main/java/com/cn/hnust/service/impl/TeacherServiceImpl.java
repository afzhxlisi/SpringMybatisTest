package com.cn.hnust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cn.hnust.dao.ITeacherDao;
import com.cn.hnust.pojo.Teacher;
import com.cn.hnust.pojo.TeacherTExample;
import com.cn.hnust.pojo.User;
import com.cn.hnust.pojo.UserTExample;
import com.cn.hnust.service.ITeacherService;

@Service("teacherService")
public class TeacherServiceImpl implements ITeacherService {
	@Resource
	private ITeacherDao userDao;

	
	@Override
	public Teacher getTeacherById(int userId){
		return this.userDao.selectByPrimaryKey(userId);
	}
	
	@Override
	public List<Teacher> getTeacherByExample(TeacherTExample userEx){
		return this.userDao.selectByExample(userEx);
	}
	
	

	
	@Override
	public void testForInitAge(){
		Teacher u = new Teacher();
		u.setAge(0);
		u.setId(1);
		this.userDao.updateByPrimaryKeySelective(u);
	}
	
//	@Override
//	@Transactional(propagation=Propagation.REQUIRES_NEW)
//	public void testRequireNew(boolean isThrow,boolean isThrow0,boolean isThrow1){
//		Teacher u = new Teacher();
//		u.setAge(1);
//		u.setId(1);
//		this.userDao.updateByPrimaryKeySelective(u);
//		if(isThrow){
//			throw new RuntimeException();
//		}
//		testRequireNew1(isThrow1);
//		if(isThrow0){
//			throw new RuntimeException();
//		}
//	}
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	@Override
	public void testRequireNew1(boolean isThrow1){
		Teacher u = new Teacher();
		u.setAge(2);
		u.setId(1);
		this.userDao.updateByPrimaryKeySelective(u);
		if(isThrow1){
			throw new RuntimeException();
		}
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	@Override
	public void testRequireSup(boolean isThrow1){
		Teacher u = new Teacher();
		u.setAge(2);
		u.setId(1);
		this.userDao.updateByPrimaryKeySelective(u);
		if(isThrow1){
			throw new RuntimeException();
		}
	}

}
