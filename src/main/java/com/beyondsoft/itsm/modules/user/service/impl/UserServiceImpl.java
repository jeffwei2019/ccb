package com.beyondsoft.itsm.modules.user.service.impl;

import java.util.List;

import javax.jws.WebService;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beyondsoft.itsm.modules.user.dao.UserMapper;
import com.beyondsoft.itsm.modules.user.dao.UserRespository;
import com.beyondsoft.itsm.modules.user.domain.User;
import com.beyondsoft.itsm.modules.user.service.IUserService;

@Service
//@WebService(targetNamespace="http://service.beyondsoft.itsm.com/",endpointInterface = "com.beyondsoft.itsm.modules.user.service.IUserService")
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRespository userRespository;

	@Override
	public User findById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<User> list() {
		return userMapper.list();
	}
	
	@Override
	@Transactional
	public void save(User user) {
		userRespository.save(user);
	}
}
