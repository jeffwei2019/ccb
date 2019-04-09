package com.beyondsoft.itsm.core.autoconfig;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * mybatis自动配置
 * 
 * @author jeff
 *
 */
@Configuration
@EnableConfigurationProperties(MybatisProperties.class)
@ConditionalOnClass({ SqlSessionFactoryBean.class, MapperScannerConfigurer.class })
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class MybatisAutoConfiguration {
	private final MybatisProperties mybatisProperties;

	private final DruidDataSource dataSource;

	public MybatisAutoConfiguration(MybatisProperties mybatisProperties, DruidDataSource dataSource) {
		this.mybatisProperties = mybatisProperties;
		this.dataSource = dataSource;
	}

	@Bean
	@ConditionalOnMissingBean
	public SqlSessionFactoryBean sqlSessionFactoryBean() {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);

		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource mybatisConfigXml = resolver.getResource(mybatisProperties.getResouce());
		sqlSessionFactoryBean.setConfigLocation(mybatisConfigXml);

		// sqlSessionFactoryBean.setMapperLocation(resolver.getResource(mybatisProperties.getMapperLocations()));
		sqlSessionFactoryBean.setTypeAliasesPackage(mybatisProperties.getTypeAliasesPackage());
		return sqlSessionFactoryBean;
	}
}
