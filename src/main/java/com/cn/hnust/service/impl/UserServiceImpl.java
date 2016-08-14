package com.cn.hnust.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cn.hnust.dao.IUserDao;
import com.cn.hnust.pojo.User;
import com.cn.hnust.pojo.UserTExample;
import com.cn.hnust.service.ITeacherService;
import com.cn.hnust.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao userDao;

	@Autowired
	private ITeacherService teacherService;
	
	@Override
	public User getUserById(int userId) {
		return this.userDao.selectByPrimaryKey(userId);
	}
	
	@Override
	public List<User> getUserByExample(UserTExample userEx){
		return this.userDao.selectByExample(userEx);
	}
	
	
	@Override
	@Transactional
	public User test(){
		User user = getUserById(1);
		user.setAge(1);
		this.userDao.updateByPrimaryKey(user);
		return user;
	}
	
	@Override
	public void testForInitAge(){
		User u = new User();
		u.setAge(0);
		u.setId(1);
		this.userDao.updateByPrimaryKeySelective(u);
	}
	
	@Override
	public int testForOpLock(){
		UserTExample userEx = new UserTExample();
		userEx.createCriteria().andIdEqualTo(1);
		List<User> us = getUserByExample(userEx);
		if(us.size()>0){
			User u = us.get(0);
			UserTExample uEx = new UserTExample();
			uEx.createCriteria().andIdEqualTo(u.getId()).andAgeEqualTo(u.getAge());
			u.setAge(u.getAge()+1);
			return this.userDao.updateByExample(u, uEx);
		}
		return 0;
	}
	
	@Override
	@Transactional
	public int testForPesLock(){
		User u = this.userDao.selectByPrimaryKeyForUpdate(1);
		u.setAge(u.getAge()+1);
		return this.userDao.updateByPrimaryKey(u);
		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void testRequireNew(boolean isThrow,boolean isThrow0,boolean isThrow1){
		
		if(isThrow){
			throw new RuntimeException();
		}
		teacherService.testRequireNew1(isThrow1);
		if(isThrow0){
			throw new RuntimeException();
		}
		User u = new User();
		u.setAge(1);
		u.setId(1);
		this.userDao.updateByPrimaryKeySelective(u);
		User user = getUserById(1);
		
	}
//	@Transactional(propagation=Propagation.REQUIRES_NEW)
//	public void testRequireNew1(boolean isThrow1){
//		User u = new User();
//		u.setAge(2);
//		u.setId(1);
//		this.userDao.updateByPrimaryKeySelective(u);
//		if(isThrow1){
//			throw new RuntimeException();
//		}
//	}

}
