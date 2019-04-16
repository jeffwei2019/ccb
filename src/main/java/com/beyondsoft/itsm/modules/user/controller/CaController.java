package com.beyondsoft.itsm.modules.user.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beyondsoft.itsm.core.BaseOutVO;
import com.beyondsoft.itsm.core.exception.ResponseStatus;
import com.beyondsoft.itsm.core.utils.CaObject;
import com.beyondsoft.itsm.core.utils.CaWebServiceUtil;

@RestController
@RequestMapping("/ca")
public class CaController {
	private static final Logger logger = LoggerFactory.getLogger(CaController.class);

	@RequestMapping(path = "/login")
	public BaseOutVO login() {
		Integer sid = CaWebServiceUtil.fromDefault().getSid();
		logger.info("登陆返回sid：{}", sid);
		if (sid > 0) {
			return BaseOutVO.success(sid);
		}
		return BaseOutVO.fail(ResponseStatus.ERROR);
	}

	@RequestMapping(path = "/create/z_app")
	public BaseOutVO createApp() {
		CaWebServiceUtil caWebServiceUtil = CaWebServiceUtil.fromDefault();
		CaObject caObject = new CaObject();
		caObject.setObjectType("z_app");
		
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("description", "12345678");
		params.put("sym", "jeff");
		params.put("z_project_manager", "111222");
		
		caObject.setAttrVals(params);
		caWebServiceUtil.createObject(caObject);
		return BaseOutVO.success();
	}
}
