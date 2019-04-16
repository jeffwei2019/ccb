package com.beyondsoft.itsm.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.beyondsoft.itsm.core.interceptor.WebAccessLogInterceptor;

/**
 * springMvc配置类
 * @author jeff
 *
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	/**
	 *   拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(webAccessLogInterceptor());
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index1").setViewName("/index1");
	}

	
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		// 路径参数如果带”.”的话，”.”后面的值将被忽略 eg:/anno/pathvar/xx.yy
		configurer.setUseSuffixPatternMatch(false);
	}

	/**
	 * web访问拦截器
	 * @return
	 */
	@Bean
	public WebAccessLogInterceptor webAccessLogInterceptor() {
		return new WebAccessLogInterceptor();
	}

}
