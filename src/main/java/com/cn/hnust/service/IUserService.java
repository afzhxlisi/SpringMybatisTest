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

	void testRequireNew(boolean isThrow, boolean isThrow0, boolean isThrow1);
}
