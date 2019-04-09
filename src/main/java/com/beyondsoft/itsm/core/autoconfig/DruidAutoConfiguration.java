package com.beyondsoft.itsm.core.autoconfig;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
@EnableConfigurationProperties(DruidProperties.class)
@ConditionalOnClass({ DataSource.class, DruidDataSource.class })
@ConditionalOnProperty(name = "druid.enabled", havingValue = "true", matchIfMissing = true)
public class DruidAutoConfiguration {
	private final DruidProperties druidProperties;

	public DruidAutoConfiguration(DruidProperties druidProperties) {
		Assert.notNull(druidProperties.getUrl(), "数据库url为空");
		Assert.notNull(druidProperties.getUsername(), "数据库用户名为空");
		this.druidProperties = druidProperties;
	}

	@Bean(destroyMethod = "close")
	@ConditionalOnMissingBean(DataSource.class)
	@ConfigurationProperties(prefix = "druid")
	public DruidDataSource duridDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUsername(this.druidProperties.getUsername());
		druidDataSource.setPassword(this.druidProperties.getPassword());
		druidDataSource.setUrl(this.druidProperties.getUrl());

		if (StringUtils.hasText(this.druidProperties.getUrl())
				&& !StringUtils.hasText(this.druidProperties.getDriverClassName())) {
			druidDataSource
					.setDriverClassName(DatabaseDriver.fromJdbcUrl(this.druidProperties.getUrl()).getDriverClassName());
		} else {
			druidDataSource.setDriverClassName(this.druidProperties.getDriverClassName());
		}
		return druidDataSource;
	}

	@Bean
	@ConditionalOnProperty(name = "druid.monitor", havingValue = "true", matchIfMissing = false)
	public ServletRegistrationBean druidServlet() {
		return new ServletRegistrationBean(new StatViewServlet(), druidProperties.getMonitorServletUrlPattern());
	}

	@Bean
	@ConditionalOnProperty(name = "druid.monitor", havingValue = "true", matchIfMissing = false)
	public FilterRegistrationBean druidFilter() {
		WebStatFilter filter = new WebStatFilter();
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(filter);
		
		HashMap<String,String> initParameters = new HashMap<String,String>();
		initParameters.put("exclusions", "*.js,*.css,/druid*,*.jsp,*.swf,*.jpg");
		initParameters.put("principalSessionName", "sessionInfo");
		initParameters.put("profileEnable", "true");
		
		filterRegistrationBean.setInitParameters(initParameters);
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
		 
	}
}
