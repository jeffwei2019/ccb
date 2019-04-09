package com.beyondsoft.itsm.modules.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beyondsoft.itsm.core.BaseOutDto;
import com.beyondsoft.itsm.modules.user.domain.User;
import com.beyondsoft.itsm.modules.user.service.IUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping(path = "/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;

	@RequestMapping(path = "/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public User findById(@PathVariable("userId") Integer userId) {
		return userService.findById(userId);
	}

	@RequestMapping(path = "/list", method = RequestMethod.GET)
	@ResponseBody
	public BaseOutDto<List<User>> list(int currentPage,int pageSize) {
		Page page = PageHelper.startPage(currentPage, pageSize);
		return BaseOutDto.success(userService.list());
	}

	@RequestMapping(path = "/userftlList", method = RequestMethod.GET)
	public String ftlList(ModelMap map) {
		List<User> userList = userService.list();
		map.put("userList", userList);
		return "user/userList";
	}
	
	@RequestMapping(path = "/userJspList", method = RequestMethod.GET)
	public String jspList(ModelMap map) {
		List<User> userList = userService.list();
		map.put("userList", userList);
		return "user/userJspList";
	}

	@RequestMapping(path = "/save", method = RequestMethod.GET)
	@ResponseBody
	public BaseOutDto save(User user) {
		userService.save(user);
		return BaseOutDto.success();
	}
}
