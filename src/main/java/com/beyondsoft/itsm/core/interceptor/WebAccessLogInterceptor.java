package com.beyondsoft.itsm.core.interceptor;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * web请求参数日志记录拦截器
 * @author jeff
 *
 */
public class WebAccessLogInterceptor extends HandlerInterceptorAdapter {
	private final Logger logger = LoggerFactory.getLogger(WebAccessLogInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map<String, String[]> params = request.getParameterMap();
		final String url = request.getRequestURI();
		logger.info("================本次请求地址：{}", url);
		if (null != params && !params.isEmpty()) {
			for (Map.Entry<String, String[]> entry : params.entrySet()) {
				String paramName = entry.getKey(); // 参数名
				String paramValue = Arrays.toString(entry.getValue());
				logger.info("{}:{}", paramName, paramValue);
			}
		}
		return true;
	}
}
