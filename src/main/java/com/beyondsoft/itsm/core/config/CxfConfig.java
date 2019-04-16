package com.beyondsoft.itsm.core.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.beyondsoft.itsm.modules.user.service.IUserService;

/**
 * cxf配置类 请求地址以http://127.0.0.1:9001/service开头
 * eg:http://127.0.0.1:9001/service/user?wsdl
 * 
 * @author jeff
 *
 */
@Configuration
@ConditionalOnClass({ CXFServlet.class, SpringBus.class })
@ConditionalOnProperty(name = "cxf.enabled", havingValue = "true")
public class CxfConfig {
	@Autowired
	private SpringBus springBus;

	@Autowired
	private IUserService userService;

	@Bean
	public Endpoint userEndpoint() {
		EndpointImpl userEndpoint = new EndpointImpl(springBus, userService);
		userEndpoint.publish("/user"); // 显示要发布的名称
		return userEndpoint;
	}
}
