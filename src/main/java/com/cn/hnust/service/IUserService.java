package com.cn.hnust.service;

import java.util.List;

import com.cn.hnust.pojo.User;
import com.cn.hnust.pojo.UserTExample;

public interface IUserService {
	public User getUserById(int userId);

	User test();

	List<User> getUserByExample(UserTExample userEx);

	int testForOpLock();

	void testForInitAge();

	int testForPesLock();

	void testRequireNew(boolean isThrowOut,boolean isThrowInner);

	void testRequireNever(boolean isThrowOut,boolean isThrowInner);

	void testRequireMan(boolean isThrowOut,boolean isThrowInner);

	void testRequireNest(boolean isThrowOut,boolean isThrowInner);

	void testRequireNest1(boolean isThrowOut,boolean isThrowInner);

	void testRequireCompNest(boolean isThrowOut,boolean isThrowInner);
}
