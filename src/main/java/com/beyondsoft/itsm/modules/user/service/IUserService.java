package com.beyondsoft.itsm.modules.user.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.beyondsoft.itsm.modules.user.domain.User;

@WebService
public interface IUserService {

	@WebMethod
	User findById(@WebParam(name = "userId") Integer id);

	@WebMethod
	List<User> list();

	@WebMethod
	void save(User user);

}