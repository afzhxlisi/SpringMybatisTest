package org.zsl.testmybatis;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)		//��ʾ�̳���SpringJUnit4ClassRunner��
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

public class TestMyBatis {
	private static Logger logger = Logger.getLogger(TestMyBatis.class);
//	private ApplicationContext ac = null;
	@Resource
	private IUserService userService ;

//	@Before
//	public void before() {
//		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		userService = (IUserService) ac.getBean("userService");
//	}

	@Test
	public void test1() {
		try{
		User user = userService.getUserById(1);
		logger.info(JSON.toJSONString(user));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		// System.out.println(user.getUserName());
		// logger.info("ֵ��"+user.getUserName());
		
	}
}