package com.beyondsoft.itsm.core.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "mybatis")
public class MybatisProperties {
	private String resouce; // SqlMapConfig.xml文件位置-基于 classpath:方式 classpath:mybatis/SqlMapConfig.xml
	private String typeAliasesPackage; // 类别名对应的包路�?(mapper.xml中可以采用包别名)
	private String mapperLocations; // mapper文件路径-基于 classpath:方式
	private String basePackage; // mapper接口包路�?

	public MybatisProperties() {
		System.out.println("=============MybatisProperties============");
	}

	public String getResouce() {
		return resouce;
	}

	public void setResouce(String resouce) {
		System.out.println("=============setResouce============");
		this.resouce = resouce;
	}

	public String getTypeAliasesPackage() {
		return typeAliasesPackage;
	}

	public void setTypeAliasesPackage(String typeAliasesPackage) {
		this.typeAliasesPackage = typeAliasesPackage;
	}

	public String getMapperLocations() {
		return mapperLocations;
	}

	public void setMapperLocations(String mapperLocations) {
		this.mapperLocations = mapperLocations;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}
}
