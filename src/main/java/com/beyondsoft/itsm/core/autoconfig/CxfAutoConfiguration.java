package com.beyondsoft.itsm.core.autoconfig;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({ CXFServlet.class,SpringBus.class })
@ConditionalOnProperty(name = "cxf.enabled", havingValue = "true")
public class CxfAutoConfiguration {

	private final static String DEAULT_CXF_SERVLET_URL_PATTERN = "/service/*";

	private final String cxfServletUrLPattern = DEAULT_CXF_SERVLET_URL_PATTERN;

	@Bean
	public ServletRegistrationBean cxfServlet() {
		return new ServletRegistrationBean(new CXFServlet(), cxfServletUrLPattern);
	}

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}
	
}
