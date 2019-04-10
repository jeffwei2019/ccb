package com.beyondsoft.itsm.core.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "druid")
public class DruidProperties {
	private String url;
	private String username;
	private String password;
	private String driverClassName;

	/**
	 *  druid连接池监控servlet拦截uri
	 */
	private static final String DEFAULT_MONITOR_SERVLET_URL_PATTERN = "/druid/*";

	private String monitorServletUrlPattern = DEFAULT_MONITOR_SERVLET_URL_PATTERN;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {

		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getMonitorServletUrlPattern() {
		return monitorServletUrlPattern;
	}

	public void setMonitorServletUrlPattern(String monitorServletUrlPattern) {
		this.monitorServletUrlPattern = monitorServletUrlPattern;
	}

}
