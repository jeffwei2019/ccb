package com.beyondsoft.itsm.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.beyondsoft.itsm.BaseTest;
import com.beyondsoft.itsm.modules.user.domain.User;
import com.beyondsoft.itsm.modules.user.service.IUserService;

import junit.framework.Assert;


public class UserServiceTest extends BaseTest{
	@Autowired
	private IUserService userService;
	
	@Test
	public void testList() {
		List<User> userList = userService.list();
		Assert.assertTrue(userList.size() > 0);
	}
	
	@Test
	public void testSave() {
		User user = new User();
		user.setUsername("jackson");
		user.setCreateTime(System.currentTimeMillis());
		user.setAge(20);
		user.setPassword("123456");
		
		userService.save(user);
	}
}
