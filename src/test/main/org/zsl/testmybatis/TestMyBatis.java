package org.zsl.testmybatis;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.cn.hnust.pojo.User;
import com.cn.hnust.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
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
			userService.testForInitAge();
			Date dt1 = new Date();
			int length = 1000;
			ExecutorService es = Executors.newFixedThreadPool(length);
			for(int i = 0;i<length;i++){
				es.execute(new Runnable() {
					
					@Override
					public void run() {
						int sucess = 0;
						for(;;){
							sucess = userService.testForOpLock();
							if(sucess > 0){
								break;
							}else{
							}
						}
					}
				});
			}
			es.shutdown();
			while(!es.isTerminated()){
				Thread.sleep(10);
			}
			System.out.println(new Date().getTime()-dt1.getTime());
			User user = userService.getUserById(1);
			logger.info(JSON.toJSONString(user));
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Test
	public void test2() {
		try{
			userService.testForInitAge();
			Date dt1 = new Date();
			int length = 1000;
			ExecutorService es = Executors.newFixedThreadPool(length);
			for(int i = 0;i<length;i++){
				es.execute(new Runnable() {
					
					@Override
					public void run() {
						userService.testForPesLock();
					}
				});
			}
			es.shutdown();
			while(!es.isTerminated()){
				Thread.sleep(10);
			}
			System.out.println(new Date().getTime()-dt1.getTime());
			User user = userService.getUserById(1);
			
			logger.info(JSON.toJSONString(user));
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
